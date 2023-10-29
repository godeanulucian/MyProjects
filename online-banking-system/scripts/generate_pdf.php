<?php

require('tcpdf/tcpdf.php');

function generatePDF()
{
    $pdf = new TCPDF(PDF_PAGE_ORIENTATION, PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);

    $pdf->SetCreator(PDF_CREATOR);
    $pdf->SetTitle('Payment History');
    $pdf->SetHeaderData('', '', 'Payment History', '');
    $pdf->setHeaderFont(array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_MAIN));
    $pdf->setFooterFont(array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));

    $pdf->AddPage();

    // include HTML content in the PDF
    ob_start();
    include('../pages/payments.php');

    $html = ob_get_contents();
    ob_end_clean();

    // output the HTML content to the PDF
    $pdf->writeHTML($html, true, 0, true, 0);

    // close the PDF document and saves it in my path
    $pdf->lastPage();
    $pdfFilePath = 'C:\Users\lucia\Downloads';
    $pdf->Output($pdfFilePath, 'F');
}

generatePDF();
?>