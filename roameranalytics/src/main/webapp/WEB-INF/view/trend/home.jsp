<div ng-if="isHomeTab()" ng-controller="HomeController">
	 <div>
		<heat-map />
	</div> 
</div>
<div ng-if="isTrendTab()" ng-controller="TrendController">
	<div>
		<trends />
	</div>
</div>
<div ng-if="isMicroSegmentTab()"  ng-controller="MicroSegmentController">Microsegment Body</div>
<div ng-if="isBeforeTravelTab()"  ng-controller="BeforeTravelController">Before Travel Body</div>
<div ng-if="isUponLandingTab()"  ng-controller="UponLandingController">Upon Landing Body</div>