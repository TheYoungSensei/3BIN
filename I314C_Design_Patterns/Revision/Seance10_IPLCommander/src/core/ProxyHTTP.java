package core;

import remote.HTTPUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ProxyHTTP implements Fichier {

    private String url;
    private boolean charge;
    private boolean sauve;
    private boolean efface;
    private boolean liste;

    public ProxyHTTP(String url, boolean charge, boolean sauve, boolean efface, boolean liste) {
        this.url = url;
        this.charge = charge;
        this.sauve = sauve;
        this.efface = efface;
        this.liste = liste;
    }

    @Override
    public String[] liste(String repertoire) throws IOException {
        if (liste) {
            Map<String, String> params = new HashMap<>();
            params.put("action", "liste");
            return HTTPUtils.performGetCall(url+"/"+repertoire, params).split("\n");
        }
        throw new IllegalAccessError();
    }

    @Override
    public byte[] charge(String chemin) throws IOException {
        if (charge) {
           Map<String, String> params = new HashMap<>();
           params.put("action", "charge");
           params.put("chemin", chemin);
           return Base64.getDecoder().decode(HTTPUtils.performGetCall(url, params));
        }
        throw new IllegalAccessError();
    }

    @Override
    public void sauve(String chemin, byte[] donnees) throws IOException {
        if (sauve) {
            Map<String, String> params = new HashMap<>();
            params.put("action", "sauve");
            params.put("chemin", chemin);
            params.put("donnees", Base64.getEncoder().encodeToString(donnees));
            HTTPUtils.performPostCall(url, params);
        } else {
            throw new IllegalAccessError();
        }
    }

    @Override
    public void efface(String chemin) throws IOException {
        if (efface) {
            Map<String, String> params = new HashMap<>();
            params.put("action", "efface");
            params.put("chemin", chemin);
            HTTPUtils.performPostCall(url, params);
        } else {
            throw new IllegalAccessError();
        }
    }
}
