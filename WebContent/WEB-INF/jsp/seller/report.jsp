<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
	<title>${title }</title>
	<%@include file="cssinclude.jsp" %>
</head>
<body>


	<div id="wrapper">
		<%@include file="dashbord-sidemenu.jsp" %>
	
		<div id="page-content-wrapper">
            <div class="page-content inset">
                <div class="row">
                    <div class="col-md-12">

                        <div class="row">
                            <div class="col-xs-4 col-xs-offset-8">
                                <label style="font-size: 1.2em" class="label label-success"><span>${userName}<span></label>
                            </div>
                        </div>
                                                
                        <div style="margin-top: 80px;"></div>
                        
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                                <div class="col-xs-12">
                                    <select name="productName" class="col-xs-7">
                                        <optgroup label="Select any product">
                                            <option value="1">Product1</option>
                                            <option value="1">Product1</option>
                                            <option value="1">Product1</option>
                                            <option value="1">Product1</option>
                                            <option value="1">Product1</option>
                                        </optgroup>
                                    </select>
                                    <select name="time" class="col-xs-4 col-xs-offset-1">
                                        <option value="3">Last 3 month</option>
                                        <option value="6">Last 6 month</option>
                                        <option value="12">Last Year</option>
                                    </select>
                                </div>

                                <section class="content">
                                    <div class="row">
                                        <div class="panel panel-default" style="margin-top: 60px;">
                                            <!-- LINE CHART -->
                                            <div class="panel-heading">
                                                <h3>Report for product 1 .</h3>
                                            </div>
                                            <div class="box-body chart-responsive">
                                                <div class="chart" id="line-chart" style="height: 300px;"></div>
                                            </div><!-- /.box-body -->
                                        </div><!-- /.box -->
                                    </div><!-- /.col (RIGHT) -->
                                </section><!-- /.content -->
                            </div>
                        </div>
                        
                   </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="jsinclude.jsp" %>
    <script type="text/javascript" src="../resources/js/raphael-min.js"></script>
    <script>
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("active");
        });
    </script>
    <script type="text/javascript">
        $(function () {


            var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            // LINE CHART
            var line = new Morris.Line({
                element: 'line-chart',
                resize: true,
                data: [
                    { month: '2014-01', value: 350 },
                    { month: '2014-02', value: 440 },
                    { month: '2014-03', value: 440 },
                    { month: '2014-04', value: 405 },
                    { month: '2014-05', value: 390 },
                    { month: '2014-06', value: 112 },
                    { month: '2014-07', value: 123 },
                    { month: '2014-08', value: 321 },
                    { month: '2014-09', value: 123 },
                    { month: '2014-10', value: 123 },
                    { month: '2014-11', value: 123 },
                    { month: '2014-12', value: 123 }
                ],
                xkey: 'month',
                ykeys: ['value'],
                xLabelFormat: function (x) { return months[x.getMonth()]; },
                labels: ['Total Sell '],
                lineColors: ['#3c8dbc'],
                hideHover: 'auto'
            });

        });
    </script>

</body>