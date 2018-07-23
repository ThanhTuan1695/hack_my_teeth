<%@page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container">
	
        <h1 class="my-4">Welcome to Dentist Booking</h1>

        <!-- Marketing Icons Section -->
        <h3 class="my-4">All Dentist:  </h3>
        <div class="row">
            <c:if test="${listDentist.size() > 0}">
                <c:forEach items="${listDentist}" var="dentist">
                    <div class="col-lg-4 mb-4">
                        <div class="card h-100">
                            <h4 class="card-header">${dentist.firstName}</h4>
                            <div class="card-body">
                                <p class="card-text">
                                    <span><strong>ID:</strong> ${dentist.userID}</span><br>
                                    <span><strong>Fullname:</strong> ${dentist.lastName} ${dentist.firstName}</span><br>
                                    <span><strong>Email:</strong> ${dentist.email}</span>
                                </p>
                            </div>
                            <div class="card-footer">
                                <a href="/appointment" class="btn btn-primary">Appointment</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${listDentist.size() <= 0}">
                <p>The dentist is empty!!!</p>
            </c:if>

        </div>
        <!-- /.row -->

        <!-- Features Section -->
        <div class="row">
            <div >
                <h2>Modern Dentist Features</h2>
                <p>All Service:</p>
                <ul>
                    <li>Search</li>
                    <li>
                        <strong>Booking </strong>
                    </li>
                    <li>Contact with them</li>
                    <li>Send a message</li>
                </ul>
            </div>

        </div>
        <!-- /.row -->

        <hr>

        <!-- Call to Action Section -->
        <div class="row mb-4">
            <div class="col-md-12">
                <p>We work in IT Security Department of mgm Technology Partners. If you have any questions, please contact us:</p>
                <ul>
                    <li>Dennis.Stoetzel@mgm-sp.com-<em> Managing Principal IT Security Department</em> </li>
                    <li>Dentist.team@mgm-sp.com-<em> Member of IT Security Department</em> </li>
                </ul>
            </div>

        </div>
    </div>
</t:layout>