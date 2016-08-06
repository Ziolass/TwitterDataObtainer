package utils.input;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.*;


/**
 * Created by Michau on 03.08.2016.
 */
public class InputParametersHandler {


    /**
     * Funkcja wczytujaca parametry wywolania aplikacji i ustawiajaca odpowiednie wartosci
     */

    private static final Logger log = Logger.getLogger(InputParametersHandler.class.getName());
    private String[] args = null;
    private Options options = new Options();

    public InputParametersHandler(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        options.addOption("l", "lang", true, "Chosen language filter");
        options.addOption("k", "key", true, "Chosen keywords");
    }

    public InputParametersHolder getInputParameters() {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        List<String> languages = null;
        List<String> keywords = null;


        try {
            cmd = parser.parse(options, args);
        } catch (Exception e) {
            e.printStackTrace();
            help();
        }

        if (cmd.hasOption("h")) {
            help();
        }

        if (cmd.hasOption("l")) {
            String langValue = cmd.getOptionValue("l");
            languages = InputParametersParser.parseInputValues(langValue);
            log.log(Level.INFO, "Using languages parameters -l= "+ languages.toString());
        }
        if (cmd.hasOption("k")) {
            String keyValues = cmd.getOptionValue("k");
            keywords = InputParametersParser.parseInputValues(keyValues);
            log.log(Level.INFO, "Using keywords argument -k=" + keywords.toString());

        } else {
            log.log(Level.SEVERE, "Missing v option");
            help();
        }
        return new InputParametersHolder(languages,keywords);
    }

    private void help() {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("Podane parametry wejsciowe sa nieprawidlowe, wywolaj program jeszcze raz z podanymi parametrami:", options);
        System.exit(0);
    }
}

