<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modern Business - Start Bootstrap Template</title>
    <!-- Bootstrap core CSS -->
    
    <link href="/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
      <!-- Bootstrap core JavaScript -->
    <script src="/static/vendor/jquery/jquery.min.js"></script>
    <script src="/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

     
   
    <!-- Custom styles for this template -->
    <link href="/static/css/modern-business.css" rel="stylesheet">

    </head>

    <body>

    <!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
    <a class="navbar-brand" href="/home">Dentist Booking</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav ml-auto">
    <form class="form-inline my-2 my-lg-0" action="/search" method="get">
    <input class="form-control mr-sm-2" type="text" placeholder="Search" name="keywords" aria-label="Search">
    <div class"input-btn"><button id="submit" type="submit"><ion-icon name="search"></ion-icon></button></div> 
    </form>
    <li class="nav-item">
    <a class="nav-link" href="/home">Home</a>
    </li>
        <li class="nav-item">
    <a class="nav-link" href="/setting">Setting</a>
    </li>
    <c:choose>
    <c:when test="${not empty username}">
	    <li class="nav-item">
	    <a class="nav-link" href="#">
		    <c:if test="${role == 1}">
		    Dentist
		    </c:if>
		    <c:if test="${role == 0}">
		    Customer
		    </c:if>
		    <strong><c:out value="${username}"/></strong>
		    
		    </a>
	    </li>
	    <li class="nav-item">
	    <a class="nav-link" href="/logout">Log out</a>
	    </li>
    </c:when>
    <c:otherwise>
    	   <li class="nav-item">
	    <a class="nav-link" href="/login">Login</a>
	    </li>
    </c:otherwise>
    </c:choose>
    </ul>

    </div>
    </div>
    </nav>


    <!-- Page Content -->
        <jsp:doBody/>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
    <div class="container">
    <p class="m-0 text-center text-white">Copyright &copy; By Dentist Team 2018</p>
    </div>
    <!-- /.container -->
    </footer>
    </body>

    </html>
