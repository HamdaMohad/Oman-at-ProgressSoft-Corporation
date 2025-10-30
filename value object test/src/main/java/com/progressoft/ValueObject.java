package com.progressoft;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

class ValueObject implements Comparable<ValueObject> {
    private final Map<String, Object> fields;

    protected ValueObject(Object... keyValuePairs) {
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException("Must provide key-value pairs");
        }
        
        this.fields = new LinkedHashMap<>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = (String) keyValuePairs[i];
            Object value = keyValuePairs[i + 1];
            fields.put(key, value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("{");
        
        boolean first = true;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            first = false;
        }
        
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int compareTo(ValueObject other) {
        if (this.getClass() != other.getClass()) {
            return this.getClass().getName().compareTo(other.getClass().getName());
        }
        
        Object[] thisValues = fields.values().toArray();
        Object[] otherValues = other.fields.values().toArray();
        
        int minLength = Math.min(thisValues.length, otherValues.length);
        for (int i = 0; i < minLength; i++) {
            int cmp = compareValues(thisValues[i], otherValues[i]);
            if (cmp != 0) {
                return cmp;
            }
        }
        
        return Integer.compare(thisValues.length, otherValues.length);
    }

    @SuppressWarnings("unchecked")
    private int compareValues(Object v1, Object v2) {
        if (v1 == v2) return 0;
        if (v1 == null) return -1;
        if (v2 == null) return 1;
        
        if (v1 instanceof Comparable && v2 instanceof Comparable) {
            return ((Comparable) v1).compareTo(v2);
        }
        
        return v1.toString().compareTo(v2.toString());
    }
}
