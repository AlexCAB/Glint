package glint.model
import glint.database.{EmbeddedDBConnector, Connector}


/**
 * Trait used for definition of data model container
 * Created by CAB on 01.03.2015.
 */

class Model(val connector:Connector = new EmbeddedDBConnector("")) {


}
