<!DOCTYPE HTML>
<html manifest="">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=10, user-scalable=yes">

    <title>Quick Start</title>
	
	<link href="webjars/extjs/6.2.0/build/modern/theme-material/resources/theme-material-all-debug.css" rel="stylesheet" type="text/css"/>
	<link href="webjars/extjs/6.2.0/build/packages/ux/modern/modern-neptune/resources/ux-all-debug.css" rel="stylesheet" type="text/css"/>
	<link href="webjars/extjs/6.2.0/build/packages/charts/modern/modern-neptune/resources/charts-all-debug.css" rel="stylesheet" type="text/css"/>
	<link href="webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="Branding.action?CSS&template=apptemplate.jsp&v=${appVersion}" rel="stylesheet" type="text/css"/>
	
	
    <!-- The line below must be kept intact for Sencha Cmd to build your application -->
    <!-- <script id="microloader" data-app="9b26bc93-57e1-4173-910f-d75951525cfc" type="text/javascript" src="webjars/extjs/6.2.0/ext-bootstrap.js"></script> -->
	<script src="webjars/extjs/6.2.0/build/ext-modern-all-debug.js" type="text/javascript"></script>
	
	<script src="webjars/extjs/6.2.0/build/modern/theme-material/theme-material.js" type="text/javascript"></script>
	<script src="webjars/extjs/6.2.0/build/packages/ux/modern/ux-debug.js" type="text/javascript"></script>
	<script src="webjars/extjs/6.2.0/build/packages/charts/modern/charts-debug.js" type="text/javascript"></script>	

	
	
	<script src="Mobile/app.js" type="text/javascript"></script>
	
	<style type="text/css">
		/**
		* Example of an initial loading indicator.
		* It is recommended to keep this as minimal as possible to provide instant feedback
		* while other resources are still being loaded for the first time
		*/
		html, body {
			height: 100%;
			background-color: #1985D0
		}

		#appLoadingIndicator {
			position: absolute;
			top: 50%;
			margin-top: -15px;
			text-align: center;
			width: 100%;
			height: 30px;
			-webkit-animation-name: appLoadingIndicator;
			-webkit-animation-duration: 0.5s;
			-webkit-animation-iteration-count: infinite;
			-webkit-animation-direction: linear;
		}

		#appLoadingIndicator > * {
			background-color: #FFFFFF;
			display: inline-block;
			height: 30px;
			border-radius: 15px;
			-webkit-border-radius: 15px;
			margin: 0 5px;
			width: 30px;
			opacity: 0.8;
		}

		@-webkit-keyframes appLoadingIndicator{
			0% {
				opacity: 0.8
			}
			50% {
				opacity: 0
			}
			100% {
				opacity: 0.8
			}
		}
	</style>	
	
</head>
<body>
	<div id="appLoadingIndicator">	
		<div></div>
		<div></div>
		<div></div>
	</div>	
	<script type="text/javascript">
		/*
		Ext.onReady(function(){
			
			Ext.Viewport.add({
				xtype: 'panel',
				html: 'Hello World!'
			});

			
		});
		*/
	</script>
	
</body>
</html>