package ro.teamnet.zth;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
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
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static javafx.scene.input.KeyCode.P;
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
                        String methodUrl = myRequestMethod.urlPath();

                        String urlPath = controllerUrlPath + methodUrl;

                        MethodAttributes attributes = new MethodAttributes();
                        attributes.setMethodName(controllerMethod.getName());
                        attributes.setMethodType(myRequestMethod.methodType());
                        attributes.setControllerClass(controller.getName());
                        attributes.setParameterType(controllerMethod.getParameterTypes());
                        allowedMethods.put(urlPath, attributes);
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
            r = dispatch(resp, req);
        }catch(Exception e){
            e.printStackTrace();
            sendExceptionError(e, req, resp);
        }

        try {
            reply(r, req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Object dispatch(HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        //resp.getWriter().write("A mers");
        String path = req.getPathInfo();

        if (allowedMethods.containsKey(path)) {
            MethodAttributes methAtt = allowedMethods.get(path);
            String controllerName = methAtt.getControllerClass();
            try {
                Class<?> controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();

                Method method = controllerClass.getMethod(methAtt.getMethodName(), methAtt.getParameterType());

                Parameter[] parameters = method.getParameters();
                List<Object> paramenterValues = new ArrayList<>();
                for(Parameter parameter : parameters){
                    if(parameter.isAnnotationPresent(MyRequestParam.class)){
                        MyRequestParam annotation = parameter.getAnnotation(MyRequestParam.class);
                        String name = annotation.name();
                        String requestParameterValue = req.getParameter(name);
                        Class<?> type = parameter.getType();
                        Object Obj = new ObjectMapper().readValue(requestParameterValue, type);
                        paramenterValues.add(Obj);
                    }
                }
                return method.invoke(controllerInstance, paramenterValues.toArray());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }


        /*if (path.startsWith("/employees")) {
            EmployeeController empController = new EmployeeController();
            return empController.getAllEmployees();
        }
        else{
            if(path.startsWith("/departments")){
                DepartmentController depController = new DepartmentController();
                return depController.getAllDepartments();
            }
        }*/
        return "Ce faci tu aici?!"+req.getParameter("id");
    }

    protected void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(r);
        resp.getWriter().write(json);

    }

    protected void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp){

    }
}
