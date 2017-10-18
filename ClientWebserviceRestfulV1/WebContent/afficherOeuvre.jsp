<%@ include file="include/header.jsp"%>
<body>
	<div id="site">
		<%@ include file="include/menu.jsp"%>
		<div id="conteneur">
			<%@ include file="include/bandeaudroite.jsp"%>

			<div id="contenu">
			<P align="center">
			<FONT face="Arial" size="5" color="#004080"> <STRONG>Modification de l'oeuvre </STRONG></FONT>
			</P>
			
			<h1>Affichage  de l'oeuvre </h1>

			<form method="post" action="Controleur?action=modifierOeuvre">
			<div>
				<ul>
				<li><b>ID : </b><input type="text" name="txtId" value="${uneOeuvre.idOeuvrevente}" readonly/> </li>
				<li><b>Titre  : </b> <input type="text" name="txtTitre" value="${uneOeuvre.titreOeuvrevente}" /></li>
				<li><b>Etat  : </b>
					<select name="txtEtat">
							<option value="L"> L </option>
							<option value="R"> R </option>
					</select>
				</li>
				<li><b>Prix : </b> <input type="text" name="txtPrix" value ="${uneOeuvre.prixOeuvrevente}" /></li>
				<li><b>Id Proprietaire :</b>
					<input type="text" name="txtIdProp" value="${uneOeuvre.proprietaire.idProprietaire}" readonly/></li>

			</ul>
			<input type="submit" value="Modifier" />
			</div>

			</form>

		</div>
			<%@ include file="include/footer.jsp"%>
		</div>
	</div>
	
</body>
</html>
