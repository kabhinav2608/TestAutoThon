package model;


/**
 * @author kumar on 29/08/18
 * @project X-search
 */

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "Source",
    "Value"
})
public class Rating {
  @JsonProperty("Source")
  public String source;
  @JsonProperty("Value")
  public String value;
  @JsonProperty("Source")
  public String getSource() {
    return source;
  }

  @JsonProperty("Source")
  public void setSource(String source) {
    this.source = source;
  }

  @JsonProperty("Value")
  public String getValue() {
    return value;
  }

  @JsonProperty("Value")
  public void setValue(String value) {
    this.value = value;
  }
}
