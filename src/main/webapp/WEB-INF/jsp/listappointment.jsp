<%@page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="m" uri="/WEB-INF/tld/xssTag.tld"%>
<%@ page session="false" %>

    <t:layout>
    <div class="container">
		
        <h3 class="my-4">List of Appointments</h3>
		
        <!-- Marketing Icons Section -->
        <h3 class="my-4">All Appointments:  </h3>
        <div class="row">
            <c:if test="${listapp.size() > 0}">
                <c:forEach items="${listapp}" var="appointment">
                    <div class="col-lg-4 mb-4">
                        <div class="card h-100">
                            <h4 class="card-header">Dentist: ${appointment.denName}</h4>
                            <div class="card-body">
                                <p class="card-text">

                                <c:choose>
                                	<c:when test="${not empty xssProtection }">
	                                    <span><strong>Booked by:</strong> ${appointment.cusName}</span><br>
	                                    <span><strong>Title:</strong><input style="border: none;display: inline;padding: none;width: auto;" type="text" name="title" value="${m:escapeHtml(appointment.title)}" readonly="readonly" ></span><br>
	                                    <span><strong>Date time:</strong> ${appointment.time}</span>
	                                    <span>
	                                    	<strong>Description:</strong>${m:escapeHtml(appointment.description)}
	                                    </span>
	                                   
                                    </c:when>
                                    <c:otherwise>
                                    	 <span><strong>Booked by:</strong> ${appointment.cusName}</span><br>
	                                    <span><strong>Title:</strong><input style="border: none;display: inline;padding: none;width: auto;" type="text" name="title" value="${appointment.title}" readonly="readonly" ></span><br>
	                                    <span><strong>Date time:</strong> ${appointment.time}</span>
	                                    <span>
	                                    	<strong>Description:</strong>${appointment.description}
	                                    </span>
                                    </c:otherwise>
                                   </c:choose>

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
		<c:choose>
			<c:when test="${not empty xssProtection}">
				<script>
					var alertText = "Latest Booking: " + "${m:escapeJS(lastBooking)}";
					alert(alertText);
				</script>
			</c:when>
			<c:otherwise>
					<script>
					var alertText = "Latest Booking: " + "${lastBooking}";
					alert(alertText);
				</script>
			</c:otherwise>
		</c:choose>
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
