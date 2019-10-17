<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String menu="m1";
	
	if(request.getParameter("menu")!=null){
		menu = request.getParameter("menu");
		
	}

%>
<!-- Sidebar Wrapper -->
        <div id="mws-sidebar">
            <!-- Main Navigation -->
            <div id="mws-navigation">
            	<ul>
                	<li id="m1" class="active"><a href="dayTotal.jsp?menu=m1" class="mws-i-24 i-home">整体情况</a></li>
				    <li  id="m9"><a href="Business.jsp?menu=m9" class="mws-i-24 i-stopatch">时段统计</a></li>
					<li  id="m10"><a href="weekBusiness.jsp?menu=m10" class="mws-i-24 i-clipboard-1">按周统计</a></li>
					<li  id="m6"><a href="histroy_month.jsp?menu=m6" class="mws-i-24 i-day-calendar">月度统计</a></li>
                	<li  id="m2"><a href="histroy.jsp?menu=m2" class="mws-i-24 i-graph">年度统计</a></li>
					<li  id="m5"><a href="hotpoint.jsp?menu=m5" class="mws-i-24 i-favorite">热点业务</a></li>
					<li>
					<a href="#" class="mws-i-24 i-list">窗口效能</a>
					    <ul>
					    <li id="m7"><a href="windowCount.jsp?menu=m7">时效统计</a></li>
						<li id="m8"><a href="WindowServerCount.jsp?menu=m8">业务量统计</a></li>
					    </ul>
					</li>
                	<li  id="m4"><a href="areaCount.jsp?menu=m4" class="mws-i-24 i-globe">区域比较</a></li>

                	<!--

                	<li>
                    	<a href="#" class="mws-i-24 i-list">Forms</a>
                        <ul>
                        	<li><a href="form_layouts.html">Layouts</a></li>
                        	<li><a href="form_elements.html">Elements</a></li>
                        </ul>
                    </li>

                	<li>
                    	<a href="icons.html" class="mws-i-24 i-pacman">
                        	Icons <span class="mws-nav-tooltip">2000+</span>
                        </a>
                    </li>
					-->
                </ul>
            </div>
            <!-- End Navigation -->
            
        </div>
		<script  type="text/javascript" >
		//设换菜单
			function changeMenu(id){

				$("#mws-navigation li").removeClass("active");

				$("#"+id).addClass("active");
				
			}
			$(function() {
				changeMenu("<%=menu%>");
			});
		</script>