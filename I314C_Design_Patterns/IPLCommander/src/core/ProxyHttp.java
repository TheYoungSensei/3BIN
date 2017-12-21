package core;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import remote.HTTPUtils;

public class ProxyHttp implements Fichier {

    private String url;

    public ProxyHttp(String url) {
        this.url = url;
    }

    @Override
    public String[] liste(String repertoire) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("action", "liste");
        return HTTPUtils.performGetCall(url + "/" + repertoire, params).split("\n");
    }

    @Override
    public byte[] charge(String chemin) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("action", "charge");
        params.put("chemin", chemin);
        return Base64.getDecoder().decode(HTTPUtils.performGetCall(url, params));
    }

    @Override
    public void sauve(String chemin, byte[] donnees) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("action", "sauve");
        params.put("chemin", chemin);
        params.put("donnees", Base64.getEncoder().encodeToString(donnees));
        HTTPUtils.performPostCall(url, params);
    }

    @Override
    public void efface(String chemin) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("action", "efface");
        params.put("chemin", chemin);
        HTTPUtils.performPostCall(url, params);
    }
}
