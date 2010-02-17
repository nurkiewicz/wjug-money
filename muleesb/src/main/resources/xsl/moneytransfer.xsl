<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/com.google.groups.warszawajug.money.MoneyTransfer">
	<html>
	<head></head>
	<body style="background-color: #eef">	
		<h1>Money transfer confirmation</h1>
		<h2>Amount: <xsl:value-of select="amount"/> EUR</h2>
		<hr/>
		<h2>To:</h2>
		<h4><xsl:value-of select="accountOwner/name"/>, <xsl:value-of select="accountOwner/country"/></h4>
		<b>Street</b>: <xsl:value-of select="accountOwner/street"/><br/>
		<b>Zip/city</b>: <xsl:value-of select="accountOwner/postalCode"/>, <xsl:value-of select="accountOwner/city"/><br/>
		<hr/>
		<h2>Account: <xsl:value-of select="accountNo"/></h2>
		<b>Bank</b>: <xsl:value-of select="ownerBank/name"/> (<i><xsl:value-of select="ownerBank/country"/></i>)<br/>
		<hr/>
	</body>
	</html>
</xsl:template>
</xsl:stylesheet>
