package admin1.ufm.quanlybanhangfahasa.DinhDang_Fahasa;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Calendar;

public class DinhDangNgay implements TextWatcher {
    private static final String DDMMYYYY = "DDMMYYYY";
    private static final String SEPARATOR = "/";

    private final Calendar calendar = Calendar.getInstance();

    private String currentText = "";

    private EditText editText;

    public DinhDangNgay(EditText editText)  {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(this.currentText)) {
            // Remove all non-digit.
            String newTextClean = s.toString().replaceAll("[^\\d.]|\\.", "");
            String currentTextClean = this.currentText.replaceAll("[^\\d.]|\\.", "");

            int newTextLength = newTextClean.length();

            // Cursor Position Index.
            int selectionIndex = newTextLength;
            for (int i = 2; i <= newTextLength && i < 6; i += 2) {
                selectionIndex++;
            }
            // Fix for pressing delete next to a forward slash
            if (newTextClean.equals(currentTextClean))  {
                selectionIndex--;
            }

            if (newTextClean.length() < 8) {
                newTextClean = newTextClean + this.DDMMYYYY.substring(newTextClean.length());
            } else {
                // This part makes sure that when we finish entering numbers
                // the date is correct, fixing it otherwise
                int day  = Integer.parseInt(newTextClean.substring(0,2));
                int month  = Integer.parseInt(newTextClean.substring(2,4));
                int year = Integer.parseInt(newTextClean.substring(4,8));

                month = month < 1 ? 1 : month > 12 ? 12 : month;
                this.calendar.set(Calendar.MONTH, month-1);

                year = (year < 1900)? 1900:(year > 2100)? 2100 : year;
                this.calendar.set(Calendar.YEAR, year);

                // ^ first set year for the line below to work correctly
                // with leap years - otherwise, date e.g. 29/02/2012
                // would be automatically corrected to 28/02/2012

                day = (day > this.calendar.getActualMaximum(Calendar.DATE))? this.calendar.getActualMaximum(Calendar.DATE):day;

                newTextClean = String.format("%02d%02d%02d",day, month, year);
            }
            // "%s/%s/%s"
            String format = "%s" + SEPARATOR + "%s" + SEPARATOR +"%s";
            newTextClean = String.format(format, newTextClean.substring(0, 2),
                    newTextClean.substring(2, 4),
                    newTextClean.substring(4, 8));

            selectionIndex = selectionIndex < 0 ? 0 : selectionIndex;
            this.currentText = newTextClean;

            this.editText.setText(this.currentText);
            this.editText.setSelection(selectionIndex < this.currentText.length() ? selectionIndex : this.currentText.length());
        }
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}
