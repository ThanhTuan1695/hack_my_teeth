<%@page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container">

        <h1 class="my-4"></h1>


        <!-- Features Section -->
        <div class="row">
            <c:choose>
                <c:when test="${dentist == null}">
                    <div class="col-lg-12 text-center">
                        <p class="alert alert-danger">Dentist not found</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12">
                        <div class="col-lg-4">
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
                        <div class="col-lg-12 text-center">
                            <h2>Doctor : ${dentist.lastName} </h2>
                            <div>
                                <h6>Information: </h6>
                                <p> Fullname :  ${dentist.firstName} ${dentist.lastName}</p>
                                <p> Email Contact:  ${dentist.email}</p>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>


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