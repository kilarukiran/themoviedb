package org.github.kilarukirankumar.repository.datasource;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * TLSSocketFactory
 */

public class TLSSocketFactory extends SSLSocketFactory {

    private static final String TAG = "TLSSocketFactory";

    private static TLSSocketFactory instance;

    private SSLSocketFactory delegate;

    private TLSSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext context = SSLContext.getInstance("TLS");
        final TrustManager[] trustedCertificates = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[0];
                    }
                }
        };
        context.init(null, trustedCertificates, new java.security.SecureRandom());
        delegate = context.getSocketFactory();
    }

    public static TLSSocketFactory getInstance() {
        if (instance == null) {
            try {
                instance = new TLSSocketFactory();
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return instance;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
            throws IOException, UnknownHostException {
        return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            List<String> enabledProtocols = new ArrayList<String>(Arrays.asList(((SSLSocket) socket).getEnabledProtocols()));
            printEnabledProtocolsInDebugMode(enabledProtocols);
            if (enabledProtocols.size() > 1 && enabledProtocols.contains("SSLv3")) {
                enabledProtocols.remove("SSLv3");
                Log.d(TAG, "enableTLSOnSocket() - Removed SSLv3 from enabled protocols ");
            } else {
                Log.d(TAG, "enableTLSOnSocket() " + "SSL stuck with protocol available for " + String.valueOf(enabledProtocols));
            }

            enabledProtocols = new ArrayList<String>(Arrays.asList(((SSLSocket) socket).getEnabledProtocols()));
            printEnabledProtocolsInDebugMode(enabledProtocols);
        }
        return socket;
    }

    private void printEnabledProtocolsInDebugMode(List<String> enabledProtocols) {
        /*if (BuildConfig.DEBUG) {
            for (String enabledProtocol : enabledProtocols) {
                Log.d(TAG, "enableTLSOnSocket() - enabledProtocol: " + enabledProtocol);
            }
        }*/
    }
}
