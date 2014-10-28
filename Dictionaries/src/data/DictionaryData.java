package data;

import java.util.Comparator;

/**
 * Created by arseny on 22.10.14.
 */
public class DictionaryData implements Comparable<DictionaryData> {

    private String name;
    private String description;
    private String expectedResult;

    public DictionaryData() {
        name = "";
        description = "";
        expectedResult = "";
    }

    public String getName() {
        return name;
    }

    public DictionaryData setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DictionaryData setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public DictionaryData setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryData that = (DictionaryData) o;

        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(DictionaryData o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }

    @Override
    public String toString() {
        return "DictionaryData[" + name + ", " + description + "]";
    }
}
