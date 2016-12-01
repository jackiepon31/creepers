<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Main content starts -->

<div class="content">

  
  	<!-- Main bar -->
  	<div class="mainbar">

      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-table"></i> 客户管理</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="/index"><i class="icon-home"></i> Home</a> 
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
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">综合查询</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>

                <div class="widget-content">
                  <div class="padd">
                    <!-- Form starts.  -->
                     <form class="form-horizontal" role="form">
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">客户名称</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="customer_name"  class="form-control" placeholder="">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">电话号码</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="telephone"  class="form-control" placeholder="">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-4 control-label">手机号码</label>
                                  <div class="col-lg-8">
                                    <input type="text"  name="mobile"  class="form-control" placeholder="">
                                  </div>
                                </div>
                                <div  class="form-group">
                                  <label class="col-lg-4 control-label">生效日期</label>
                                  <div class="col-lg-8">
                                     <input type="text"  name="start_dt"  class="form-control" placeholder="">
                                  </div>
                               </div>
                               <div  class="form-group">
                                  <label class="col-lg-4 control-label">结束日期</label>
                                  <div class="col-lg-8">
                                     <input type="text"  name="end_dt"  class="form-control" placeholder="">
                                  </div>
                               </div>
                                <div class="form-group">
                                  <div class="col-lg-offset-1 col-lg-9">
                                    <button type="button" class="btn btn-default btn-lg ">重置</button>
                                    <button type="button" class="btn btn-primary btn-lg ">提交</button>
                                  </div>
                                </div>
                              </form>
                  </div>
                </div>
              </div>  
            </div>
           
           <!-- Table -->
            <div class="row">
              <div class="col-md-12">
                <div class="widget">
                <div class="widget-head">
                  <div class="pull-left">客户列表</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                    <table class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>客户名称</th>
                          <th>电话号码</th>
                          <th>手机号码</th>
                          <th>生效日期</th>
                          <th>结束日志</th>
                          <th>状态信息</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>

                        <tr>
                          <td>1</td>
                          <td>Ravi Kumar</td>
                          <td>India</td>
                          <td>23/12/2012</td>
                          <td>Paid</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>
                          <td>
                              <button class="btn btn-xs btn-success"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>
                          </td>
                        </tr>


                        <tr>
                          <td>2</td>
                          <td>Parneethi Chopra</td>
                          <td>USA</td>
                          <td>13/02/2012</td>
                          <td>Free</td>
                          <td>Paid</td>
                          <td><span class="label label-danger">Banned</span></td>
                          <td>
                              <button class="btn btn-xs btn-default"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-remove"></i> </button>
                          </td>
                        </tr>

                        <tr>
                          <td>3</td>
                          <td>Kumar Ragu</td>
                          <td>Japan</td>
                          <td>12/03/2012</td>
                          <td>Paid</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>
                          <td>
                            <div class="btn-group">
                              <button class="btn btn-xs btn-default"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-remove"></i> </button>
                            </div>
                          </td>
                        </tr>

                        <tr>
                          <td>4</td>
                          <td>Vishnu Vardan</td>
                          <td>Bangkok</td>
                          <td>03/11/2012</td>
                          <td>Paid</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>
                          <td>
                            <div class="btn-group">
                              <button class="btn btn-xs btn-success"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>
                            </div>
                          </td>
                        </tr>

                        <tr>
                          <td>5</td>
                          <td>Anuksha Sharma</td>
                          <td>Singapore</td>
                          <td>13/32/2012</td>
                          <td>Free</td>
                          <td>Paid</td>
                          <td><span class="label label-danger">Banned</span></td>
                          <td>
                            <div class="btn-group1">
                              <button class="btn btn-xs btn-success"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>
                            </div>
                          </td>
                        </tr>                                                            

                      </tbody>
                    </table>

                    <div class="widget-foot">
                        <ul class="pagination pull-right">
                          <li><a href="#">Prev</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">Next</a></li>
                        </ul>
                      <div class="clearfix"></div> 
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