package core.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Parser JSON manual sem uso de bibliotecas externas.
 * Suporta objetos, arrays, strings, números, booleanos e null.
 */
public class JsonParser {
    private String json;
    private int pos;

    public JsonParser(String json) {
        this.json = json;
        this.pos = 0;
    }

    /**
     * Carrega um ficheiro JSON e retorna o conteúdo parseado
     */
    public static Object parseFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return parse(content);
    }

    /**
     * Faz parse de uma String JSON
     */
    public static Object parse(String json) {
        JsonParser parser = new JsonParser(json);
        return parser.parseValue();
    }

    private Object parseValue() {
        skipWhitespace();

        if (pos >= json.length()) {
            throw new JsonParseException("JSON vazio");
        }

        char ch = json.charAt(pos);

        switch (ch) {
            case '{':
                return parseObject();
            case '[':
                return parseArray();
            case '"':
                return parseString();
            case 't':
            case 'f':
                return parseBoolean();
            case 'n':
                return parseNull();
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return parseNumber();
            default:
                throw new JsonParseException("Caractere inesperado: " + ch + " na posição " + pos);
        }
    }

    private Map<String, Object> parseObject() {
        Map<String, Object> obj = new LinkedHashMap<>();
        pos++; // Skip '{'
        skipWhitespace();

        if (pos < json.length() && json.charAt(pos) == '}') {
            pos++;
            return obj;
        }

        while (pos < json.length()) {
            skipWhitespace();

            // Parse key
            if (json.charAt(pos) != '"') {
                throw new JsonParseException("Chave esperada na posição " + pos);
            }
            String key = parseString();

            skipWhitespace();
            if (pos >= json.length() || json.charAt(pos) != ':') {
                throw new JsonParseException("':' esperado na posição " + pos);
            }
            pos++;

            // Parse value
            Object value = parseValue();
            obj.put(key, value);

            skipWhitespace();
            if (pos >= json.length()) {
                break;
            }

            char ch = json.charAt(pos);
            if (ch == '}') {
                pos++;
                break;
            } else if (ch == ',') {
                pos++;
            } else {
                throw new JsonParseException("',' ou '}' esperado na posição " + pos);
            }
        }

        return obj;
    }

    private List<Object> parseArray() {
        List<Object> arr = new ArrayList<>();
        pos++; // Skip '['
        skipWhitespace();

        if (pos < json.length() && json.charAt(pos) == ']') {
            pos++;
            return arr;
        }

        while (pos < json.length()) {
            arr.add(parseValue());
            skipWhitespace();

            if (pos >= json.length()) {
                break;
            }

            char ch = json.charAt(pos);
            if (ch == ']') {
                pos++;
                break;
            } else if (ch == ',') {
                pos++;
            } else {
                throw new JsonParseException("',' ou ']' esperado na posição " + pos);
            }
        }

        return arr;
    }

    private String parseString() {
        pos++; // Skip opening quote
        StringBuilder sb = new StringBuilder();

        while (pos < json.length()) {
            char ch = json.charAt(pos);

            if (ch == '"') {
                pos++;
                return sb.toString();
            } else if (ch == '\\') {
                pos++;
                if (pos >= json.length()) {
                    throw new JsonParseException("Escape sequence incompleto");
                }
                char escaped = json.charAt(pos);
                switch (escaped) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        if (pos + 4 >= json.length()) {
                            throw new JsonParseException("Unicode escape incompleto");
                        }
                        String hex = json.substring(pos + 1, pos + 5);
                        sb.append((char) Integer.parseInt(hex, 16));
                        pos += 4;
                        break;
                    default:
                        throw new JsonParseException("Escape sequence inválido: \\" + escaped);
                }
                pos++;
            } else {
                sb.append(ch);
                pos++;
            }
        }

        throw new JsonParseException("String não fechada");
    }

    private Number parseNumber() {
        int start = pos;

        if (json.charAt(pos) == '-') {
            pos++;
        }

        if (pos >= json.length() || !Character.isDigit(json.charAt(pos))) {
            throw new JsonParseException("Número inválido");
        }

        if (json.charAt(pos) == '0') {
            pos++;
        } else {
            while (pos < json.length() && Character.isDigit(json.charAt(pos))) {
                pos++;
            }
        }

        boolean isFloat = false;

        if (pos < json.length() && json.charAt(pos) == '.') {
            isFloat = true;
            pos++;
            if (pos >= json.length() || !Character.isDigit(json.charAt(pos))) {
                throw new JsonParseException("Número inválido após ponto decimal");
            }
            while (pos < json.length() && Character.isDigit(json.charAt(pos))) {
                pos++;
            }
        }

        if (pos < json.length() && (json.charAt(pos) == 'e' || json.charAt(pos) == 'E')) {
            isFloat = true;
            pos++;
            if (pos < json.length() && (json.charAt(pos) == '+' || json.charAt(pos) == '-')) {
                pos++;
            }
            if (pos >= json.length() || !Character.isDigit(json.charAt(pos))) {
                throw new JsonParseException("Número inválido na notação exponencial");
            }
            while (pos < json.length() && Character.isDigit(json.charAt(pos))) {
                pos++;
            }
        }

        String numStr = json.substring(start, pos);

        try {
            if (isFloat) {
                return Double.parseDouble(numStr);
            } else {
                try {
                    return Integer.parseInt(numStr);
                } catch (NumberFormatException e) {
                    return Long.parseLong(numStr);
                }
            }
        } catch (NumberFormatException e) {
            throw new JsonParseException("Número inválido: " + numStr);
        }
    }

    private Boolean parseBoolean() {
        if (json.startsWith("true", pos)) {
            pos += 4;
            return true;
        } else if (json.startsWith("false", pos)) {
            pos += 5;
            return false;
        }
        throw new JsonParseException("Booleano inválido na posição " + pos);
    }

    private Object parseNull() {
        if (json.startsWith("null", pos)) {
            pos += 4;
            return null;
        }
        throw new JsonParseException("Null inválido na posição " + pos);
    }

    private void skipWhitespace() {
        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
            pos++;
        }
    }

    /**
     * Exceção customizada para erros de parsing
     */
    public static class JsonParseException extends RuntimeException {
        public JsonParseException(String message) {
            super(message);
        }

        public JsonParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Helper para obter um valor do mapa como String
     */
    public static String getString(Map<String, Object> obj, String key) {
        Object value = obj.get(key);
        return value != null ? value.toString() : null;
    }

    /**
     * Helper para obter um valor do mapa como Integer
     */
    public static Integer getInteger(Map<String, Object> obj, String key) {
        Object value = obj.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return null;
    }

    /**
     * Helper para obter um valor do mapa como Double
     */
    public static Double getDouble(Map<String, Object> obj, String key) {
        Object value = obj.get(key);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return null;
    }

    /**
     * Helper para obter um valor do mapa como Boolean
     */
    public static Boolean getBoolean(Map<String, Object> obj, String key) {
        Object value = obj.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return null;
    }

    /**
     * Helper para obter um valor do mapa como Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getObject(Map<String, Object> obj, String key) {
        Object value = obj.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        }
        return null;
    }

    /**
     * Helper para obter um valor do mapa como List
     */
    @SuppressWarnings("unchecked")
    public static List<Object> getArray(Map<String, Object> obj, String key) {
        Object value = obj.get(key);
        if (value instanceof List) {
            return (List<Object>) value;
        }
        return null;
    }
}
