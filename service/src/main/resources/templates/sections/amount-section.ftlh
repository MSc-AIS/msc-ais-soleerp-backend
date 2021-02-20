<table class="go-right font-size-10">
    <tbody>
    <tr class="bold-all">
        <td class="left-all padding-right">Σύνολο πλην ΦΠΑ</td>
        <td class="right-all padding-left">&euro; ${invoice.totalExcludingVATAsString}</td>
    </tr>
    <tr>
        <td class="bold-all left-all padding-right">Ποσό ΦΠΑ</td>
        <td class="right-all padding-left">&euro; ${invoice.VATAmountAsString}</td>
    </tr>
    <tr>
        <td class="bold-all left-all padding-right">Στρογγυλοποίηση</td>
        <td class="right-all padding-left">&euro; 0,00</td>
    </tr>
    <tr>
        <td class="bold-all left-all padding-right">Έκπτωση</td>
        <td class="right-all padding-left">&euro; 0,00</td>
    </tr>
    <tr class="bold-all">
        <td class="left-all padding-right">ΟΛΙΚΟ ΠΟΣΟ</td>
        <td class="right-all padding-left">&euro; ${invoice.totalAmountAsString}</td>
    </tr>
    </tbody>
</table>