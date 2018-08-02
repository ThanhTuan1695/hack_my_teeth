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
                            <h2>Booking Form For <div id="userName"></div></h2>
                            <form action="/submitApp" method="post">
                            <input type="hidden" name="cusID" id="cusID" value="${userID}">
                            <input type="hidden" name="cusID" id="cusID" value="${denID}">
                            <label>Date:</label>
                            <input type="text" name="datebook" id="datebook" value="" class="hasDatepicker">
                            <br/>
                            <label>Description:</label>
                            <input type="text" name="description" id="description">
                            <br>
                            <button class="btn btn-primary btn-block" type="submit" style="position:relative;width:30%;height:30%;">Submit</button>
                            </form>
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
	
	var jsString = "$('#userName').text('" + decodeURI(document.location.href.substring(document.location.href.indexOf("#username=")+10))   + "');$('#userName').show();";
	eval(jsString);
	
	</script>
</t:layout>
