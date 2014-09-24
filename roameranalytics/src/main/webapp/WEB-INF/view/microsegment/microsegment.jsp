	<div class="row dashboard-top">
		<div class="col-lg-3">
			<h1 class="maincontent-heading">MICRO SEGMENT</h1>
		</div>
		<div class="col-lg-7">

		<div class="tag-div" ng-repeat="filter in filters.countries">
			{{filter.name}} <a href
				ng-click='removeCounryFilter(filter.name,true)' class="delete-tag"></a>
		</div>
		<div class="tag-div" ng-repeat="filter in filters.personas">
			{{filter.name}} <a href
				ng-click='removePersonaFilter(filter.id,true)' class="delete-tag"></a>
		</div>
		<span ng-repeat="(key, value) in filters.attributes">
			<div class="tag-div" ng-repeat="filter in value">
				{{filter.name}} <a href
					ng-click='removeAttributeFilter(key,filter.attrId, filter.catId,true)'
					class="delete-tag"></a>
			</div>
		</span>
	</div>
		<div class="col-lg-2">
			<div class="commentshare-icon">
			<span class="dropdown micro-setting-area"> <a
				href="javascript:void(0)" class="micro-setting-icon cust-tooltip"
				data-toggle="dropdown" original-title="Settings"></a>
				<div
					class="dropdown-menu pull-right comment-dropdown popover bottom stay-dd">
					<div class="arrow"></div>
					<div class="commentdd-content">
						<p class="trackdd-title">Attribute measure</p>
						<ul class="micro-map-setting">
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="roamers"
										class="all-blue-map" value=""> <i></i></label> <label
										for="roamers">roamers</label>
								</p>
							</li>
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="mo"
										value="" checked> <i></i></label> <label for="mo">MO
										(Min)</label>
								</p>
							</li>
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="mt"
										class="all-blue-map" value=""> <i></i></label> <label for="mt">MT
										(Min)</label>
								</p>
							</li>
							<li>
								<p class="i-checks">
									<label> <input type="radio" name="project" id="Data"
										class="all-blue-map" value=""> <i></i></label> <label
										for="Data">Data</label>
								</p>
							</li>

							<div class="compare_btn clearfix">
								<button type="button" class="btn btn-primary pull-left">Apply</button>
							</div>
						</ul>
					</div>

				</div>
			</span> <span class="dropdown"> <a href="javascript:void(0)"
					class="greycomment-icon cust-tooltip" data-toggle="dropdown"
					original-title="Comment"></a>
					<div
						class="dropdown-menu pull-right comment-dropdown popover bottom stay-dd">
						<div class="arrow"></div>
						<div class="commentdd-content">
							<p class="trackdd-title">Add Comment</p>
							<textarea class="track-textarea"
								placeholder="Add your comments here"></textarea>
							<button type="button" class="btn btn-primary">Save</button>
							<button type="button" class="btn btn-primary unactive">Cancel</button>
						</div>
						<div class="commentdd-footer">
							Share With <a href="javascript:void(0)" class="share-public"></a>
							<a href="javascript:void(0)" class="share-frnds active"></a> <a
								href="javascript:void(0)" class="share-onlyme"></a>
						</div>
					</div>
				</span> <span class="dropdown"><a href="javascript:void(0)"
					class="microtrack-icon cust-tooltip" data-toggle="dropdown"
					original-title="Track"> </a>
					<div
						class="dropdown-menu pull-right track-dropdown popover bottom stay-dd">
						<div class="arrow"></div>
						<div class="tracker-area clearfix">
							<h2>Create Trcaker</h2>
							<div class="tracker-name-box clearfix">
								<p class="trackdd-title">Track micro segment as</p>
								<input type="text" class="track-input" placeholder="Enter Name" />
							</div>
							<p class="trackdd-title">Compare between</p>
							<div class="compare-between">
								<input type="text" class="track-input"
									placeholder="Current and Last month" /> <input type="text"
									class="track-input" placeholder="Current month and Last year" />
								<input type="text" class="track-input compare-custom"
									placeholder="Custom" />
								<div class="pull-right compare_btn clearfix">
									<button type="button" class="btn btn-primary pull-left">Save</button>
								</div>
							</div>
							<div class="compare-custom-box">
								<div class="compare-bet-area clearfix">
									<div class="compare-month pull-left">
										<p>Month</p>
										<select id="basic" class="compare-month-select select">
											<option value="">1</option>
											<option value="tray1">2</option>
											<option value="tray2">3</option>
											<option value="tray3">4</option>
											<option value="tray4">5</option>
											<option value="tray5">6</option>
											<option value="tray6">7</option>
											<option value="tray7">8</option>
											<option value="tray8">9</option>
											<option value="tray9">10</option>
											<option value="tray10">11</option>
											<option value="tray11">12</option>
											<option value="tray12">13</option>
											<option value="tray13">14</option>
											<option value="tray14">15</option>
											<option value="tray15">16</option>
											<option value="tray16">17</option>
											<option value="tray17">18</option>
										</select>
									</div>
									<div class="compare-month pull-left">
										<p>Year</p>
										<select id="basic" class="compare-month-select select">
											<option value="">2014</option>
											<option value="tray1">2013</option>
											<option value="tray2">2012</option>
											<option value="tray3">2011</option>
											<option value="tray4">2010</option>
											<option value="tray5">2009</option>
										</select>
									</div>
								</div>
								<p class="trackdd-title">&</p>
								<div class="compare-bet-area clearfix">
									<div class="compare-month pull-left">
										<p>Month</p>
										<select id="basic" class="compare-month-select select">
											<option value="">1</option>
											<option value="tray1">2</option>
											<option value="tray2">3</option>
											<option value="tray3">4</option>
											<option value="tray4">5</option>
											<option value="tray5">6</option>
											<option value="tray6">7</option>
											<option value="tray7">8</option>
											<option value="tray8">9</option>
											<option value="tray9">10</option>
											<option value="tray10">11</option>
											<option value="tray11">12</option>
											<option value="tray12">13</option>
											<option value="tray13">14</option>
											<option value="tray14">15</option>
											<option value="tray15">16</option>
											<option value="tray16">17</option>
											<option value="tray17">18</option>
										</select>
									</div>
									<div class="compare-month pull-left">
										<p>Year</p>
										<select id="basic" class="compare-month-select select">
											<option value="">2014</option>
											<option value="tray1">2013</option>
											<option value="tray2">2012</option>
											<option value="tray3">2011</option>
											<option value="tray4">2010</option>
											<option value="tray5">2009</option>
										</select>
									</div>
									<div class="pull-right compare_btn clearfix">
										<button type="button"
											class="btn btn-primary unactive pull-left">
											<i class="back-arrow"></i>Back
										</button>
										<button type="button" class="btn btn-primary pull-left">Save</button>
									</div>
								</div>
							</div>
						</div>
					</div> </span>
			</div>
		</div>
	</div>
	<div class="row microsegment-charts" ng-controller="MicroSegmentController">
		<div class="col-lg-9 clearfix">
			<div class="microchart-panel" ng-repeat="graph in graphToBeShown">
				<div class="segment-charts">
					<div class="microchart-heading">
						{{title[graph.title]}} <a href="javascript:void(0)"
							class="bookmark-unactive cust-tooltip" original-title="Bookmark"></a>
					</div>
					<div id="bar-chart-{{graph.id}}"></div>
					<donutchart class="big-donutchart" columnname="{{graph.column}}" columntype="{{graph.columnType}}" charttype='{{graph.chartType}}' chartname="{{graph.title}}" daterange='daterange'/>
					<div id="column-chart-{{graph.id}}"></div>
				</div>
				<div class="bookmark-panel">
					<a href="javascript:void(0)" class="close-bookmark"><i
						class="fa close-icon"></i></a>
					<div class="bookmarkpnl-content">
						<div class="clearfix">
							<p class="bookmarkpnl-title">Select Bookmark Tray</p>
							<select id="basic" class="select">
								<option value="">July Business Roamers</option>
								<option value="tray1">Tray 2</option>
								<option value="tray2">Tray 3</option>
								<option value="tray3">Tray 4</option>
								<option value="tray4">Tray 5</option>
								<option value="tray5">Tray 6</option>
							</select> <input type="button" class="select-btn" value="Select">
						</div>
						<p class="or-text">OR</p>
						<div class="clearfix">
							<p class="bookmarkpnl-title">Create New Tray</p>
							<input type="text" class="bookmarkpnl-textbox" value=""
								placeholder="Create New"> <input type="button"
								class="select-btn" value="Create">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

