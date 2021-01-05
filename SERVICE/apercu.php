<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    $usager = 'postgres';
    $motdepasse = 'motdepasse';
    $hote = 'localhost';
    $base = 'normandiemeteo';

    $dsn = 'pgsql:dbname='.$base.';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);

    $SQL_MOYENNES = "SELECT AVG(tauxhumidite) as moyenne FROM humidite LIMIT 8640";
    $requete2 = $basededonnees->prepare($SQL_MOYENNES);
    $requete2->execute();
    $moyennes = $requete2->fetchAll(PDO::FETCH_OBJ);
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';
    ?>

    <humidite>
        <mobile>

<?php
    foreach ($moyennes as $moyenne) 
    {
?>           
                <apercu>                	
	                    <mesureactuelle>56</mesureactuelle>
	                    <moyenne>
	                   		<journee><?=round($moyenne->moyenne)?></journee>  
	                    	<annee><?=round($moyenne->moyenne)?></annee>  
	                	</moyenne>
                </apercu>
<?php
    }
?>
        </mobile>
    </humidite>
