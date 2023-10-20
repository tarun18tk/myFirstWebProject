    <%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>

    <div class="container">
    <!-- <h2> Welcome ${name}</h2> -->
    <h3> My To-Do List</h3>
    <!-- <div> Your Todos are ${todos} </div> --> 
    <!-- todos is the attribute we took from the controller to here it has a populated todos list -->
    <!-- this style was very weird looking so : -->
    <!-- To make a better table for todo list we use JSTL tags -->
    <!-- In the JSTL tag there are many tags that can be used to make different operations in HTMl -->
    <!-- Table creation and for Loop using JSTL tags are used here -->
   
    <table class="table">
        <thead>
            <tr>
                <th>Id</th>
                <%-- <th>UserName</th> --%>
                <th>Description</th>
                <th>Target Data</th>
                <th>Progress</th>
                <th></th>
                <th></th>
            </tr>
        </thead> 
        <tbody>
            <!-- these are also jstl tags to iterate an array -->
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.id}</td>
                    <%-- <td>${todo.username}</td> --%>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <!-- a button with link to delete-todo and the url also takes in data id -->
                    <!-- this link will be mapped with a method and we can attain the parameter using @RequestParam -->
                    <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                    <td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="add-todo" class="btn btn-success">Add TODO</a>
    <!-- JS is added right above closing body tag -->
    </div>
    
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    
</body>
</html>
