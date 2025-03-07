package uk.ac.warwick.dcs.dataproc.loading;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import uk.ac.warwick.dcs.contracts.enums.TrafficLightType;
import uk.ac.warwick.dcs.contracts.lights.ActuationTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.FixedCycleTrafficLight;
import uk.ac.warwick.dcs.contracts.lights.TrafficLight;
public class TrafficLightAdapter implements JsonDeserializer<TrafficLight>{
   @Override
   public TrafficLight deserialize(JsonElement json, Type type,JsonDeserializationContext context){
        JsonObject jsonObject = json.getAsJsonObject();
        TrafficLightType lightType = TrafficLightType.valueOf(jsonObject.get("trafficLightType").getAsString());
        if(lightType == TrafficLightType.FIXEDCYCLE){
            return new FixedCycleTrafficLight();
        }
        else if(lightType == TrafficLightType.ACTUATION){
            return new ActuationTrafficLight();
        }
        throw new JsonParseException("Unknown TrafficLightType"+lightType);
   } 
}
