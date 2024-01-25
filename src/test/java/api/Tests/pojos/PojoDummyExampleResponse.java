package api.Tests.pojos;


import java.util.Objects;

public class PojoDummyExampleResponse {

    private String status;
    private PojoDummyExampleData pojoDummyExampleData;
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public PojoDummyExampleResponse() {
    }

    /**
     *
     * @param pojoDummyExampleData
     * @param message
     * @param status
     */
    public PojoDummyExampleResponse(String status, PojoDummyExampleData pojoDummyExampleData, String message) {
        super();
        this.status = status;
        this.pojoDummyExampleData = pojoDummyExampleData;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PojoDummyExampleData getData() {
        return pojoDummyExampleData;
    }

    public void setData(PojoDummyExampleData pojoDummyExampleData) {
        this.pojoDummyExampleData = pojoDummyExampleData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PojoDummyExampleResponse{" +
                "status='" + Objects.toString(status, "<null>") + '\'' +
                ", data=" + Objects.toString(pojoDummyExampleData, "<null>") +
                ", message='" + Objects.toString(message, "<null>") + '\'' +
                '}';
    }

}
