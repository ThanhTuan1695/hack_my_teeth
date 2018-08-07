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
                        <hr>

                        <div class="col-lg-12 text-center">
                            <h2>Doctor : ${dentist.lastName} </h2>
                            <div>
                                <h6>Information: </h6>
                                <p> Fullname :  ${dentist.firstName} ${dentist.lastName}</p>
                                <p> Email Contact:  ${dentist.email}</p>
                            </div>
                            <div class="clearfix"></div>
                            <hr>
                            <div>
                                <br>
                                <br>
                                <h2>Booking Form For: <c:if test="${not empty xssProtection}">${username}</c:if><div id="userName"></div></h2>
                                <br>
                                <br>
                                <div class="col-lg-6 offset-3">
                                    <form action="/dentist?id=${dentist.userID}" method="post">
                                        <div class="form-group col-md-12">
                                            <label for="title" class="col-sm-4"><strong>Title:</strong> </label>
                                            <input  type="text" name="title" id="title" value="" class="hasDatepicker form-control">

                                        </div>
                                        <div class="form-group col-md-12">
                                            <label for="datebook" class="col-sm-4"><strong>Date:</strong> </label>
                                            <input  type="text" name="datebook" id="datebook" value="" class="hasDatepicker form-control">

                                        </div>
                                        <div class="form-group col-md-12">
                                            <label for="description" class="col-lg-6"><Strong>Description :</Strong></label>
                                            <textarea class="form-control" id="description" rows="3"  name="description"></textarea>
                                        </div>
                                        <div class="form-group col-md-12 " >
                                            <button class="btn btn-primary btn-block" type="submit" >Submit</button>
                                        </div>
                                    </form>
                                </div>

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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.3.0/jquery.datetimepicker.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.3.0/jquery.datetimepicker.min.js"></script>
    <script type="text/javascript">
	$('#datebook').datetimepicker({
		
	});
	</script>
	<c:if test="${empty xssProtection}">
	<script type="text/javascript">
	
	var jsString = "$('#userName').text('" + decodeURI(document.location.href.substring(document.location.href.indexOf("#username=")+10))   + "');$('#userName').show();";
	eval(jsString);
	
	</script>
	</c:if>
</t:layout>
