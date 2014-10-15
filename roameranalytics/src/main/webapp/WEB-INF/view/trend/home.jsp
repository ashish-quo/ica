
<div ng-if="isHomeTab()">
	 <div>
		<heat-map />
	</div> 
</div>
<div ng-if="isTrendTab()">
	<div>
		<trends />
	</div>
</div>
<div ng-if="isMicroSegmentTab()">
	<div>
		<microsegment />
	</div>
</div>
<div ng-if="isBeforeTravelTab()"  ng-controller="BeforeTravelController"></div>
<div ng-if="isUponLandingTab()"  ng-controller="UponLandingController"></div>
