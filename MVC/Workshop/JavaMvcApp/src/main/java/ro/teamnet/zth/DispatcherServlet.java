package ro.teamnet.zth;

import com.sun.corba.se.spi.ior.ObjectKey;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by user on 7/14/2016.
 */
public class DispatcherServlet extends HttpServlet {
    HashMap<String, MethodAttributes> allowedMethods
            = new HashMap<String, MethodAttributes>();
    @Override
    public void init() throws ServletException {

        // key = path
        // value = atributele metodei

        // Cautare clase din pachet
        Iterable<Class> controllers = null;
        try {
            controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Class controller : controllers) {

            if(controller.isAnnotationPresent(MyController.class)) {
                MyController myAnnotation = (MyController) controller.getAnnotation(MyController.class);
                String controllerUrlPath = myAnnotation.urlPath();

                Method[] controllerMethods = controller.getMethods();
                for (Method controllerMethod : controllerMethods) {
                    if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                        MyRequestMethod myRequestMethod = controllerMethod.getAnnotation(MyRequestMethod.class);
                        controllerUrlPath += myRequestMethod.urlPath();

                        MethodAttributes attributes = new MethodAttributes();
                        attributes.setMethodName(controllerMethod.getName());
                        attributes.setMethodType(myRequestMethod.methodType());
                        attributes.setControllerClass(controller.getName());
                        allowedMethods.put(controllerUrlPath, attributes);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req,resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       dispatchReply(req, resp, "POST");
    }

    protected void dispatchReply(HttpServletRequest req, HttpServletResponse resp, String method) {

        Object r = null;
        try {
            // Transmitere spre procesare - dispatch()
            r = dispatch(req, resp);
            reply(r, req, resp);
        }catch (IOException e) {
            e.printStackTrace();
            sendExceptionError(e, req, resp);
        }catch(Exception e){
            e.printStackTrace();
            sendExceptionError(e, req, resp);
        }


        //	Transmitere raspunsului obtinut in urma procesarii catre client - reply()


        // 	Tratarea posibilelor erori de procesare - sendExceptionError()
    }

    protected Object dispatch(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String url = req.getPathInfo();

//        //  Vrific daca incepe cu /employees
//        if(url.startsWith("/employees")){
//            EmployeeController employeeController = new EmployeeController();
//            String result = employeeController.getAllEmployees();
//            return result;
//        }
//        //  Vrific daca incepe cu /departments
//        else{
//            if(url.startsWith("/departments")){
//                DepartmentController departmentController = new DepartmentController();
//                String result = departmentController.getAllDepartments();
//                return result;
//            }
//        }

        for(String urlPath : allowedMethods.keySet()){
            MethodAttributes methodAttributes = allowedMethods.get(urlPath);

            if(methodAttributes != null){
                String className = methodAttributes.getControllerClass();
                Class controllerClass = Class.forName(className);
                Object controllerInstance = controllerClass.newInstance();
                Method method =  controllerClass.getMethod(methodAttributes.getMethodName());
                Object result = method.invoke(controllerInstance);
                return result;
            }else{

            }

        }
        return "Hello";
    }

    protected void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {

            resp.getWriter().write(r.toString());

    }

    protected void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp){

    }
}
