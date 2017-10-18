<%@ include file="include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
    <div id="site">
        <%@ include file="include/menu.jsp"%>
        <div id="conteneur">
            <%@ include file="include/bandeaudroite.jsp"%>

            <div id="contenu">
                <P align="center">
                    <FONT face="Arial" size="5" color="#004080"> <STRONG>Listing&nbsp;des
                        Oeuvres </STRONG></FONT>
                </P>
                <TABLE BORDER="1">
                    <CAPTION>Tableau des Oeuvres</CAPTION>
                    <TR>
                        <TH> Numero </TH>
                        <TH> Titre  </TH>
                        <TH> Prix  </TH>
                        <TH> Statut </TH>

                    </TR>
                    <c:forEach  items="${listeOev}"  var="itemOeuvre" >
                        <tr>
                            <td>${itemOeuvre.idOeuvrevente}</td>
                            <td>${itemOeuvre.titreOeuvrevente}</td>
                            <td>${itemOeuvre.prixOeuvrevente}</td>
                            <td>${itemOeuvre.etatOeuvrevente}</td>
                            <td><a href="Controleur?action=supprimerOeuvre&id=${itemOeuvre.idOeuvrevente}">Supprimer</a>
                        </tr>
                    </c:forEach>
                </TABLE>
            </div>
            <%@ include file="include/footer.jsp"%>
        </div>
    </div>
</body>

