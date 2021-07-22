

<header class="header-black">

	<nav class="header-nav-bar">

		<a href="<%=request.getContextPath()%>/ServletAccueil"
			class="header-logo-eni"><img src="./images/logo-ENI.png"
			height="45" width="45" alt=""></a>
		<ul class="header-menu">
			<li><a class="header-nav-link"
				href="<%=request.getContextPath()%>/ServletEncherirArticleDetail">Enchères</a></li>
			<li><a class="header-nav-link"
				href="<%=request.getContextPath()%>/ServletNouvelleVente">Vendre
					un article</a></li>
			<li><a class="header-nav-link"
				href="<%=request.getContextPath()%>/ServletAfficherProfil">Mon
					Profil</a></li>
			<li><a class="header-nav-link"
				href="<%=request.getContextPath()%>/ServletDeconnecter">Déconnexion</a></li>
		</ul>
	</nav>

</header>