<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Main content starts -->

<div class="content">

  	
  	<!-- Main bar -->
  	<div class="mainbar">

      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-home"></i> Post</h2>

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
            <div class="col-md-8">

              <div class="widget">
                <div class="widget-head">
                  <div class="pull-left">Make Post</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    
                    <input type="text" class="form-control col-lg-8" placeholder="Enter Title">
                    <br />
                    <button class="btn btn-default"><i class="icon-paper-clip"></i> Add Media</button>
                    <div class="text-area">
                        <!-- Add the "cleditor" to textarea to add CLeditor -->
                        <textarea class="cleditor" name="input"></textarea>

                    </div>

                  </div>
                  <div class="widget-foot">
                    Word Count : 205
                  </div>
                </div>
              </div>  

              <div class="widget">
                <div class="widget-head">
                  <div class="pull-left">Other Stuffs</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">

                    <h6>Excerpt</h6>
                    <p>You can add your experts here.</p>
                    <div class="text-area">
                        <!-- Add the "cleditor" to textarea to add CLeditor -->
                        <textarea name="input" class="form-control col-lg-12"></textarea>

                    </div>

                    <hr />

                    <h6>Custom Field</h6>
                    <p>Add your custom fields here</p>
                    
                      <input class="form-control col-lg-12" type="text" placeholder="Name"><br />
                     <input class="form-control col-lg-12" type="text" placeholder="value">
                    

                  </div>
                  <div class="widget-foot">
                  </div>
                </div>
              </div>  
              
            </div>

            <!-- post sidebar -->

            <div class="col-md-4">

              <div class="widget">
                <div class="widget-head">
                  <div class="pull-left">Details</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">

                    <form>

                      <h6>Category</h6>
                      <hr />
                      <div class="check-box">
							<label><input type='checkbox' value='check1' /> General</label>
						</div>
						<div class="check-box">
							<label><input type='checkbox' value='check2' /> Latest News</label>
						</div>
						<div class="check-box">
							<label><input type='checkbox' value='check3' /> Health</label>
						</div>

                      <hr />
                      <h6>Tags</h6>
                      <hr />
                      <input class="form-control col-lg-12" type="text" placeholder="Tags">

                      <hr />
					<div class="buttons">
                      <button class="btn btn-default">Save Draft</button> 
                      <button class="btn btn-info">Publish</button>
                      <button class="btn btn-danger">Trash</button>
					</div>

                    </form>

                  </div>
                  <div class="widget-foot">
                    <!-- Footer goes here -->
                  </div>
                </div>
              </div>  

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