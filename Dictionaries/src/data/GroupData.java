package data;

/**
 * Created by arseny on 22.10.14.
 */
public class GroupData implements Comparable<GroupData> {

    private String name;
    private String description;
    private String expectedResult;

    public GroupData() {
        name = "";
        description = "";
        expectedResult = "";
    }

    public String getName() {
        return name;
    }

    public GroupData setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GroupData setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public GroupData setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (description != null ? !description.equals(groupData.description) : groupData.description != null)
            return false;
        if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(GroupData o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }
}
