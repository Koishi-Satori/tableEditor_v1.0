package top.kkoishi.files;

import java.util.Vector;

/**
 * @author KKoishi
 * @since java8
 * @date 2021/11/7
 */

public class FormatStream {
    public String[][] formatInput(String input) {
        String[] separateHead = input.split("#");
        int amount = Integer.parseInt(separateHead[0]);
        String[] tableFake = separateHead[1].split("\\|");
        int len = tableFake.length;
        String[] table = new String[len / 2];
        for (int i = 0;i<len;i++) {
            if (i % 2 != 0) {
                table[i / 2] = tableFake[i];
            }
        }

        String[][] tableData = new String[len / 2][amount];
        for (int i = 0;i<len / 2;i++) {
            tableData[i] = table[i].split(";");
        }

        return tableData;
    }

    public String formatOutput(Vector<Vector> dataVector, String[] columnames) {
        int amount = columnames.length;
        StringBuilder builder = new StringBuilder();

        builder.append(amount).append("#\n");
        for (Vector strings : dataVector) {
            builder.append("|");
            for (int j = 0; j < amount - 1; j++) {
                builder.append(strings.get(j)).append(";");
            }
            builder.append(strings.get(amount - 1)).append("|\n");
        }

        return builder.toString();
    }
}
