<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Main content starts -->

<div class="content">

  	<!-- Main bar -->
  	<div class="mainbar">

      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-bar-chart"></i> Charts</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="index.html"><i class="icon-home"></i> Home</a> 
          <!-- Divider -->
          <span class="divider">/</span> 
          <a href="#" class="bread-current">Dashboard</a>
        </div>

        <div class="clearfix"></div>

      </div>
      <!-- Page heading ends -->

	    <!-- Matter -->

	    <div class="matter">
        <div class="container">

          <div class="row">
            <div class="col-md-12">

              <!-- Bar Chart -->
              <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Bar Chart</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>             


                <div class="widget-content">
                  <div class="padd">
                    <!-- Barchart. jQuery Flot plugin used. -->
                    <div id="bar-chart"></div>

                    <hr />

                    <!--Bar chart stuffs -->
                    <div class="btn-group stackControls">
                        <input type="button" value="With stacking" class="btn btn-default">
                        <input type="button" value="Without stacking" class="btn btn-default">
                    </div>            

                    <div class="btn-group graphControls">

                        <input type="button" value="Bars" class="btn btn-default">
                        <input type="button" value="Lines" class="btn btn-default">
                        <input type="button" value="Lines with steps" class="btn btn-default">
                    </div>

                  </div>
                </div>

              </div>
              <!-- Bar chart ends -->

              <!-- Curve chart starts -->

              <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Curve Chart</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>             


                <div class="widget-content">
                  <div class="padd">

                    <div id="curve-chart"></div>

                    <hr />

                    <div id="hoverdata">Mouse hovers at
                    (<span id="x">0</span>, <span id="y">0</span>). <span id="clickdata"></span></div>          

                  </div>
                </div>
              </div>
                <!-- Curve chart ends -->


                <!-- Realtime chart starts -->

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Real Time Chart</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>             

                  <div class="widget-content">
                    <div class="padd">

                      <div id="live-chart"></div>
                      <hr />
                      Time Inverval: <input id="updateInterval" type="text" class="span3" value="">

                    </div>
                  </div>
                </div>

                <!-- Realtime chart ends -->

                <!-- Pie chart starts -->

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Pie Chart</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                    <div class="padd">

                      <div class="row">
                        <div class="col-md-4">
                          <div id="pie-chart"></div>
                        </div>
                        <div class="col-md-4">
                          <div id="pie-chart2"></div>
                        </div>
                        <div class="col-md-4">
                          <div id="pie-chart3"></div>
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
                <!-- Pie chart ends -->

            </div>
          </div>

        </div>
		  </div>

		<!-- Matter ends -->

    </div>

   <!-- Mainbar ends -->	    	
   <div class="clearfix"></div>

</div>
<!-- Content ends -->