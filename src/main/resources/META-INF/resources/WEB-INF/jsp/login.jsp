<html>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <body>

        
        <div class="container">
        
        <h2>    This is the login Page   </h2>
        <!-- This was get method -->
        <!-- http://localhost:8080/login?name=Tarun&password=tarun18tk -->
        <!-- we can see how data is there in URL -->
        <!-- After changing form method type to "post" it was not included. -->
        
        <form method="post">
            Name: <input type="string" name="name">
            Password : <input type="password" name="password">
            Submit: <input type="submit">
        </form>
        <!-- post-form submit button calls its own url, that is you are calling the same method again
            and this time since its taking inputs it will be calling the POST method so basically you are calling
            a url like -> "localhost::8080/login?name=Tarun,password=password" but since its post method 
            this detail wont be visible-->
      
            <!-- if not found then its not printed -->
            <div> ${error}</div> 
        </div>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
 
    </body>
</html>