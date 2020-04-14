package mappingsConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class converter {

	// inputFile
	private static String inputFile = "/Users/amirlaadhar/omht/omht_output/GACS.json";

	// les fichier d'output
	private static final String EDOALFile = "/Users/amirlaadhar/Downloads/Mappings/OAEI/CORPUS/edoalFile.rdf";
	private static final String alignmentFormatFile = "/Users/amirlaadhar/Downloads/Mappings/OAEI/CORPUS/alignmentFormat.rdf";

	final static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws IOException, JSONException {

		ArrayList<Mappings> mappingsArray = new ArrayList<>();

		// lire le ficher JSON

		String jsonFile = new String(Files.readAllBytes(Paths.get(inputFile)));
		JsonNode Mappings = jsonToNode(jsonFile);

		// parser le fichier JSON and save the mappings in an arraylist of mappings

		for (JsonNode mapping : Mappings) {

			
			String creator = mapping.get("creator").toString();
			creator = creator.substring(1, creator.length() - 1);

			// System.out.println(creator);

			// sourceContactInfo
			String sourceContactInfo = mapping.get("source_contact_info").toString();
			// sourceContactInfo =
			// sourceContactInfo.substring(1,sourceContactInfo.length()-1);

			// System.out.println(sourceContactInfo);

			String comment = mapping.get("comment").toString();
			comment = comment.substring(1, comment.length() - 1);
			// System.out.println(comment);

			// relation
			String relation = mapping.get("relation").get(0).toString();
			// System.out.println("relation= "+relation);
			// sourceOntology

			// sourceName

			// comment

			List URIs = new ArrayList<>();
			List Ontos = new ArrayList<>();

			// find the two classes (done)
			JsonNode classes = mapping.get("classes");

			JSONObject classesContent = new JSONObject(classes.toString());
			// System.out.println(classesContent.toString());

			JsonNode oneMapping = mapper.readTree(classesContent.toString());

			Iterator<Map.Entry<String, JsonNode>> classesIterator = oneMapping.fields();

			while (classesIterator.hasNext()) {

				Map.Entry<String, JsonNode> field = classesIterator.next();

				URIs.add(field.getKey());
				Ontos.add(field.getValue());

				// System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
			}
			// System.out.println(URIs);
			// System.out.println(Ontos);

			// store the parsed
			mappingsArray.add(new Mappings(Ontos, URIs, creator, sourceContactInfo, relation, comment));

		}

		int j = 0;

		// list of unique sources and targets

		List ontologies = new ArrayList<>();

		while (j < mappingsArray.size()) {
			ontologies.add(mappingsArray.get(j).getOntos().get(0).toString());
			ontologies.add(mappingsArray.get(j).getOntos().get(1).toString());
			j = j + 1;
		}

		// System.out.println(ontologies);
		
		
		// create possible pair of ontologies to be aligned

		Set<String> uniqueOntos = new HashSet<String>(ontologies);
		List<String> uniqueOntologies = new ArrayList<String>(uniqueOntos);

		System.out.println(uniqueOntologies);

		List<ArrayList<String>> ontoPairs = new ArrayList<ArrayList<String>>();

		j = 0;
		while (j < uniqueOntologies.size()) {
			int i = 0;
			while (i < uniqueOntologies.size()) {
				if (j>i) {
				ArrayList<String> pair = new ArrayList<String>();
				//System.out.println(uniqueOntologies.get(i).toString());
				pair.add(uniqueOntologies.get(i));
				pair.add(uniqueOntologies.get(j));
				ontoPairs.add(pair);
				}
				i = i + 1;
				
			}
			j = j + 1;
		}
		

		System.out.println(ontoPairs);
		System.out.println(ontoPairs.size());
		
		//search for mappings for each ontology pairs

		// filter the mappings

		// covert JSON to EDOAL file(s)

		// conversion JSON to alignment format file(s)

	}

	private static JsonNode jsonToNode(String json) {
		JsonNode root = null;
		try {
			root = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}

}
