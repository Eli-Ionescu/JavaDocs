<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" %>
<%@ page import="ro.teamnet.zth.appl.domain.Location" %>

<html>
<head>
    <title>Locations List</title>
</head>
<body>

<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Street address</td>
        <td>Postal code</td>
        <td>City</td>
        <td>State province</td>
    </tr>
    </thead>
    <tbody>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    %>
    <tr>
        <!--TODO de completat cu cod pentru a afisa detaliile
        locatiei cu id-ul trimis din locationlist.jsp in momentul
        in care se acceseaza link-ul 'View'-->

        <%
            int id = Integer.parseInt(request.getParameter("id"));
            LocationDao ld = new LocationDao();
            Location thisLocation = ld.getLocationById(id);
        %>
        <td>
            <%=thisLocation.getId()%>
        </td>

        <td>
            <%=thisLocation.getStreetAddress()%>
        </td>

        <td>
            <%=thisLocation.getPostalCode()%>
        </td>

        <td>
            <%=thisLocation.getCity()%>
        </td>

        <td>
            <%=thisLocation.getStateProvince()%>
         </td>

    </tr>

    </tbody>
</table>
<a href="locationList.jsp">Locations List</a>
</body>
</html>
