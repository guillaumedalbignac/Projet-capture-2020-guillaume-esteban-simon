<?php
    require "./accesseur/DAO.php";
    $listeHumidite = DAO::listerHumiditesApercu();
    ?><?php

    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';
    ?>

    <humidite>
        <mobile>

<?php
    foreach ($listeHumidite as $moyenne) 
    {
?>           
                <apercu>                	
	                    <mesureactuelle>56</mesureactuelle>
	                    <moyenne>
	                   		<journee>44</journee>  
	                    	<annee><?=round($moyenne->moyenne)?></annee>  
	                	</moyenne>
                </apercu>
<?php
    }
?>
        </mobile>
    </humidite>
