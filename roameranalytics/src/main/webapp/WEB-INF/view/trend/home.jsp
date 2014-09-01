<div ng-if="isTrendTab()" ng-controller="TrendController">
	<div>
		<trends />
	</div>
</div>
<div ng-if="isMicroSegmentTab()">Microsegment Body</div>
<div ng-if="isPredictTab()">Predict Body</div>