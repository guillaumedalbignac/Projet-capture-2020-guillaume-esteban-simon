<?php
    require "./accesseur/DAO.php";
    $listeHumidite = DAO::listerHumiditesMois();
    $listeHumiditeMoyennes = DAO::listerHumiditesMoyennes();

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';

    foreach ($listeHumiditeMoyennes as $moyenne) 
    {
    ?>
    <humidite>
        <bureau>
            <mois>
                <min><?=$moyenne->minimum?></min>
                <moyenne><?=round($moyenne->moyenne)?></moyenne>  
                <max><?=$moyenne->maximum?></max> 
<?php
    }
    foreach ($listeHumidite as $humidite) 
    {
?>         
                <jour>
                    <valeur><?=$humidite->jour?></valeur>
                    <min><?=$humidite->minimum?></min>
                    <moyenne><?=round($moyenne->moyenne)?></moyenne>  
                    <max><?=$humidite->maximum?></max>  
                </jour>        
<?php
    }
?>
                </mois>
            </bureau>
    </humidite>
