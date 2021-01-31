<?php
    require "./accesseur/DAO.php";
    $listeHumidite = DAO::listerHumiditesAnnee();

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';
    ?>
    <humidite>
        <bureau>
            <annee>

<?php
    foreach ($listeHumidite as $humidite) 
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
