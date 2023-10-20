    <%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
    
    <div class="container">

        <h2> ADD TODOS </h2>
        <h3> Enter details here :-</h3>

        <!-- Normal method without spring form -->
        <!-- <form method="POST" class="form">
            UserName: <input type="text" name="username"/>
            Description: <input type="text" name="description"/>
            Number of Years left: <input type="number" name="years"/>
            <input type="submit" class="btn btn-success"/>
        </form>
    </div> -->

                 <!-------- This is a way to bind todo.jsp to todoController.java ---------->
<!-- Spring form, in here we want to link the class bean with form so that information
    from the form is directly stored in bean of tha class thats why we used modelAttribute as "todo"
    because the same name i have used int the function where I am mapping model attribute to todo bean
    ...In this process we must remember that each attribute of the bean must be mapped as path and if you dont wish to input
    ...anything you can keep it as type="hidden" like done & id here are like that but as for target date i am not sure yet-->
    <form:form method="post" modelAttribute="todo">
            <form:input type="hidden" path="id"/>
            <%-- <fieldset class="mb-3">
                <form:label path="username">Username</form:label>
                <form:input type="text" path="username"/><br>
            </fieldset> --%>
            <!-- this fieldset is used to group similar things together in a form and in the whole feild we
                can apply changes -->
            <fieldset class="mb-3">
                <form:label path="description">Description</form:label>
                <form:input type="text" path="description"/>
                <!-- this form::errors tag is also provided by the springframework because we know a form can have input casualties -->
                <form:errors path="description" class="cssClass"></form:errors><br>
            </fieldset>
            <fieldset class="mb-3">
                <form:label path="targetDate">Target Date :</form:label>
                <form:input type="date" path="targetDate"></form:input>
            </fieldset>
            <form:input type="hidden" path="done"/>
            
            <!-- Number of Years left: <form:input type="text" path="targetDate"/> -->
           
            <input type="submit" class="btn btn-success"/>
        </form:form>
    </div>
   
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
 
</body>
</html>