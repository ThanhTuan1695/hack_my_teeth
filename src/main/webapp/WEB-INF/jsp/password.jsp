<%@page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container">

        <h1 class="my-4"></h1>


        <!-- Features Section -->
        <div class="row">
  
                    <div class="col-lg-12">


                        <div class="col-lg-12 text-center">
                            <h2>User : ${userName} </h2>
                            <div class="clearfix"></div>
                            <hr>
                            <div>
                                <div class="col-lg-6 offset-3">
                                    <form action="/changePassword" method="get">
                                        <div class="form-group col-md-12">
                                            <label for="title" class="col-sm-4"><strong>New password:</strong> </label>
                                            <input  type="password" name="password" id="password" value="" class="hasDatepicker form-control">
                                        </div>
                                         <div class="form-group col-md-12">
                                            <label for="title" class="col-sm-4"><strong>Repeat:</strong> </label>
                                            <input  type="password" name="password1" id="password1" value="" class="hasDatepicker form-control">
                                        </div>
                                        
                                        <div class="form-group col-md-12 " >
                                            <button class="btn btn-primary btn-block" type="submit" >Submit</button>
                                        </div>
                                    </form>
                                          	<c:if test="${not empty errorMessage}">
											<p style="color:red">${errorMessage}</p>
										</c:if>
                                </div>

                            </div>

                        </div>
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
