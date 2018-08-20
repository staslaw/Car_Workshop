<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer id="footer">
    <p>Copyright Kamil Kozlowski Grzegorz Krawczyński Stanisław Niemyski, &copy; 2018</p>
</footer>

<!-- Add Client -->
<div class="modal fade" id="addClient" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <jsp:include page="clientAdd.jsp"/>
        </div>
    </div>
</div>

<!-- Add Order -->
<div class="modal fade" id="addOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <jsp:include page="orderadd.jsp"/>
        </div>
    </div>
</div>

<!-- Edit Order -->
<%--<div class="modal fade" id="editOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
    <%--<div class="modal-dialog" role="document">--%>
        <%--<div class="modal-content">--%>
            <%--<jsp:include page="orderedit.jsp"/>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/resources/js/main.js'></script>
</body>
</html>
