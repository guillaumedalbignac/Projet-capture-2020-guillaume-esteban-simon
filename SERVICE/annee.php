<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);

    $usager = 'postgres';
    $motdepasse = 'motdepasse';
    $hote = 'localhost';
    $base = 'normandiemeteo';

    $dsn = 'pgsql:dbname='.$base.';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);

    //$SQL_LISTE_HUMIDITE = "SELECT * FROM humidite";
    $SQL_LISTE_HUMIDITE = "SELECT date_part('month',moment) as mois, MAX(tauxhumidite) as maximum, MIN(tauxhumidite) as minimum,
    AVG(tauxhumidite) as moyenne FROM humidite WHERE date_part('year', moment) = date_part('year', moment) 
    GROUP BY date_part('month',moment)";
    $requete = $basededonnees->prepare($SQL_LISTE_HUMIDITE);
    $requete->execute();
    $humidites = $requete->fetchAll(PDO::FETCH_OBJ);
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';
    ?>
    <humidite>
        <bureau>
            <annee>

<?php
    foreach ($humidites as $humidite) 
    {
?>         
                <mois>
	                <valeur><?=$humidite->mois?></valeur>
	                <min><?=$humidite->minimum?></min>
	                <moyenne><?=round($humidite->moyenne)?></moyenne>  
	                <max><?=$humidite->maximum?></max>  
	            </mois>           
<?php
    }
?>
            </annee>
        </bureau>
    </humidite>
