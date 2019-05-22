package org.smartregister.chw.malaria;

import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import org.smartregister.chw.malaria.domain.MalariaMetadata;
import org.smartregister.repository.Repository;
import org.smartregister.repository.UniqueIdRepository;
import org.smartregister.sync.ClientProcessorForJava;
import org.smartregister.sync.helper.ECSyncHelper;

import id.zelory.compressor.Compressor;

public class MalariaLibrary {
    private static MalariaLibrary instance;

    private final Context context;
    private final MalariaMetadata metadata;
    private final Repository repository;

    private int applicationVersion;
    private int databaseVersion;

    private UniqueIdRepository uniqueIdRepository;
    private ECSyncHelper syncHelper;

    private ClientProcessorForJava clientProcessorForJava;
    private Compressor compressor;

    public static void init(Context context, Repository repository, MalariaMetadata metadata, int applicationVersion, int databaseVersion) {
        if (instance == null) {
            instance = new MalariaLibrary(context, repository, metadata, applicationVersion, databaseVersion);
        }
    }

    public static MalariaLibrary getInstance() {
        if (instance == null) {
            throw new IllegalStateException(" Instance does not exist!!! Call "
                    + CoreLibrary.class.getName()
                    + ".init method in the onCreate method of "
                    + "your Application class ");
        }
        return instance;
    }

    private MalariaLibrary(Context contextArg, Repository repositoryArg, MalariaMetadata metadataArg, int applicationVersion, int databaseVersion) {
        this.context = contextArg;
        this.repository = repositoryArg;
        this.applicationVersion = applicationVersion;
        this.metadata = metadataArg;
        this.databaseVersion = databaseVersion;
    }

    public Context context() {
        return context;
    }

    public Repository getRepository() {
        return repository;
    }

    public MalariaMetadata metadata() {
        return metadata;
    }

    public int getApplicationVersion() {
        return applicationVersion;
    }

    public int getDatabaseVersion() {
        return databaseVersion;
    }

    public UniqueIdRepository getUniqueIdRepository() {
        if (uniqueIdRepository == null) {
            uniqueIdRepository = new UniqueIdRepository(getRepository());
        }
        return uniqueIdRepository;
    }

    public ECSyncHelper getEcSyncHelper() {
        if (syncHelper == null) {
            syncHelper = ECSyncHelper.getInstance(context().applicationContext());
        }
        return syncHelper;
    }


    public ClientProcessorForJava getClientProcessorForJava() {
        if (clientProcessorForJava == null) {
            clientProcessorForJava = ClientProcessorForJava.getInstance(context().applicationContext());
        }
        return clientProcessorForJava;
    }

    public void setClientProcessorForJava(ClientProcessorForJava clientProcessorForJava) {
        this.clientProcessorForJava = clientProcessorForJava;
    }

    public Compressor getCompressor() {
        if (compressor == null) {
            compressor = Compressor.getDefault(context().applicationContext());
        }
        return compressor;
    }

    /**
     * Use this method when testing.
     * It should replace org.smartregister.Context#setInstance(org.smartregister.Context, org.smartregister.repository.Repository) which has been removed
     *
     * @param context
     */
    public static void reset(Context context, Repository repository, MalariaMetadata metadata, int applicationVersion, int databaseVersion) {
        if (context != null) {
            instance = new MalariaLibrary(context, repository, metadata, applicationVersion, databaseVersion);
        }
    }
}
