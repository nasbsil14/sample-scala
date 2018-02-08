package db.test
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Areas.schema, Genders.schema, Jobs.schema, Routes.schema, Stations.schema, Stationspath.schema, Users.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Areas
   *  @param areacode Database column AreaCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column Name SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class AreasRow(areacode: Byte, name: Option[String] = None, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching AreasRow objects using plain SQL queries */
  implicit def GetResultAreasRow(implicit e0: GR[Byte], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[AreasRow] = GR{
    prs => import prs._
    AreasRow.tupled((<<[Byte], <<?[String], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Areas. Objects of this class serve as prototypes for rows in queries. */
  class Areas(_tableTag: Tag) extends profile.api.Table[AreasRow](_tableTag, Some("Test"), "Areas") {
    def * = (areacode, name, status, registerdate, updatedate) <> (AreasRow.tupled, AreasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(areacode), name, Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> AreasRow.tupled((_1.get, _2, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column AreaCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey */
    val areacode: Rep[Byte] = column[Byte]("AreaCode", O.AutoInc, O.PrimaryKey)
    /** Database column Name SqlType(VARCHAR), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("Name", O.Length(50,varying=true), O.Default(None))
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")
  }
  /** Collection-like TableQuery object for table Areas */
  lazy val Areas = new TableQuery(tag => new Areas(tag))

  /** Entity class storing rows of table Genders
   *  @param gendercode Database column GenderCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column Name SqlType(VARCHAR), Length(10,true), Default(None)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class GendersRow(gendercode: Byte, name: Option[String] = None, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching GendersRow objects using plain SQL queries */
  implicit def GetResultGendersRow(implicit e0: GR[Byte], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[GendersRow] = GR{
    prs => import prs._
    GendersRow.tupled((<<[Byte], <<?[String], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Genders. Objects of this class serve as prototypes for rows in queries. */
  class Genders(_tableTag: Tag) extends profile.api.Table[GendersRow](_tableTag, Some("Test"), "Genders") {
    def * = (gendercode, name, status, registerdate, updatedate) <> (GendersRow.tupled, GendersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(gendercode), name, Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> GendersRow.tupled((_1.get, _2, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column GenderCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey */
    val gendercode: Rep[Byte] = column[Byte]("GenderCode", O.AutoInc, O.PrimaryKey)
    /** Database column Name SqlType(VARCHAR), Length(10,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("Name", O.Length(10,varying=true), O.Default(None))
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")
  }
  /** Collection-like TableQuery object for table Genders */
  lazy val Genders = new TableQuery(tag => new Genders(tag))

  /** Entity class storing rows of table Jobs
   *  @param jobscode Database column JobsCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column Name SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class JobsRow(jobscode: Byte, name: Option[String] = None, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching JobsRow objects using plain SQL queries */
  implicit def GetResultJobsRow(implicit e0: GR[Byte], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[JobsRow] = GR{
    prs => import prs._
    JobsRow.tupled((<<[Byte], <<?[String], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Jobs. Objects of this class serve as prototypes for rows in queries. */
  class Jobs(_tableTag: Tag) extends profile.api.Table[JobsRow](_tableTag, Some("Test"), "Jobs") {
    def * = (jobscode, name, status, registerdate, updatedate) <> (JobsRow.tupled, JobsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(jobscode), name, Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> JobsRow.tupled((_1.get, _2, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column JobsCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey */
    val jobscode: Rep[Byte] = column[Byte]("JobsCode", O.AutoInc, O.PrimaryKey)
    /** Database column Name SqlType(VARCHAR), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("Name", O.Length(50,varying=true), O.Default(None))
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")
  }
  /** Collection-like TableQuery object for table Jobs */
  lazy val Jobs = new TableQuery(tag => new Jobs(tag))

  /** Entity class storing rows of table Routes
   *  @param routecode Database column RouteCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column Name SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class RoutesRow(routecode: Byte, name: Option[String] = None, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching RoutesRow objects using plain SQL queries */
  implicit def GetResultRoutesRow(implicit e0: GR[Byte], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[RoutesRow] = GR{
    prs => import prs._
    RoutesRow.tupled((<<[Byte], <<?[String], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Routes. Objects of this class serve as prototypes for rows in queries. */
  class Routes(_tableTag: Tag) extends profile.api.Table[RoutesRow](_tableTag, Some("Test"), "Routes") {
    def * = (routecode, name, status, registerdate, updatedate) <> (RoutesRow.tupled, RoutesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(routecode), name, Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> RoutesRow.tupled((_1.get, _2, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column RouteCode SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey */
    val routecode: Rep[Byte] = column[Byte]("RouteCode", O.AutoInc, O.PrimaryKey)
    /** Database column Name SqlType(VARCHAR), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("Name", O.Length(50,varying=true), O.Default(None))
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")
  }
  /** Collection-like TableQuery object for table Routes */
  lazy val Routes = new TableQuery(tag => new Routes(tag))

  /** Entity class storing rows of table Stations
   *  @param stationid Database column StationId SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column Name SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param routecode Database column RouteCode SqlType(TINYINT UNSIGNED)
   *  @param areacode Database column AreaCode SqlType(TINYINT UNSIGNED)
   *  @param zipcode Database column ZipCode SqlType(VARCHAR), Length(10,true)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class StationsRow(stationid: Byte, name: Option[String] = None, routecode: Byte, areacode: Byte, zipcode: String, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching StationsRow objects using plain SQL queries */
  implicit def GetResultStationsRow(implicit e0: GR[Byte], e1: GR[Option[String]], e2: GR[String], e3: GR[java.sql.Timestamp]): GR[StationsRow] = GR{
    prs => import prs._
    StationsRow.tupled((<<[Byte], <<?[String], <<[Byte], <<[Byte], <<[String], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Stations. Objects of this class serve as prototypes for rows in queries. */
  class Stations(_tableTag: Tag) extends profile.api.Table[StationsRow](_tableTag, Some("Test"), "Stations") {
    def * = (stationid, name, routecode, areacode, zipcode, status, registerdate, updatedate) <> (StationsRow.tupled, StationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(stationid), name, Rep.Some(routecode), Rep.Some(areacode), Rep.Some(zipcode), Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> StationsRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column StationId SqlType(TINYINT UNSIGNED), AutoInc, PrimaryKey */
    val stationid: Rep[Byte] = column[Byte]("StationId", O.AutoInc, O.PrimaryKey)
    /** Database column Name SqlType(VARCHAR), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("Name", O.Length(50,varying=true), O.Default(None))
    /** Database column RouteCode SqlType(TINYINT UNSIGNED) */
    val routecode: Rep[Byte] = column[Byte]("RouteCode")
    /** Database column AreaCode SqlType(TINYINT UNSIGNED) */
    val areacode: Rep[Byte] = column[Byte]("AreaCode")
    /** Database column ZipCode SqlType(VARCHAR), Length(10,true) */
    val zipcode: Rep[String] = column[String]("ZipCode", O.Length(10,varying=true))
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")
  }
  /** Collection-like TableQuery object for table Stations */
  lazy val Stations = new TableQuery(tag => new Stations(tag))

  /** Entity class storing rows of table Stationspath
   *  @param stationid1 Database column StationId1 SqlType(TINYINT)
   *  @param stationid2 Database column StationId2 SqlType(TINYINT)
   *  @param requiredtime Database column RequiredTime SqlType(SMALLINT), Default(None)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class StationspathRow(stationid1: Byte, stationid2: Byte, requiredtime: Option[Short] = None, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching StationspathRow objects using plain SQL queries */
  implicit def GetResultStationspathRow(implicit e0: GR[Byte], e1: GR[Option[Short]], e2: GR[java.sql.Timestamp]): GR[StationspathRow] = GR{
    prs => import prs._
    StationspathRow.tupled((<<[Byte], <<[Byte], <<?[Short], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table StationsPath. Objects of this class serve as prototypes for rows in queries. */
  class Stationspath(_tableTag: Tag) extends profile.api.Table[StationspathRow](_tableTag, Some("Test"), "StationsPath") {
    def * = (stationid1, stationid2, requiredtime, status, registerdate, updatedate) <> (StationspathRow.tupled, StationspathRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(stationid1), Rep.Some(stationid2), requiredtime, Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> StationspathRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column StationId1 SqlType(TINYINT) */
    val stationid1: Rep[Byte] = column[Byte]("StationId1")
    /** Database column StationId2 SqlType(TINYINT) */
    val stationid2: Rep[Byte] = column[Byte]("StationId2")
    /** Database column RequiredTime SqlType(SMALLINT), Default(None) */
    val requiredtime: Rep[Option[Short]] = column[Option[Short]]("RequiredTime", O.Default(None))
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")

    /** Primary key of Stationspath (database name StationsPath_PK) */
    val pk = primaryKey("StationsPath_PK", (stationid1, stationid2))
  }
  /** Collection-like TableQuery object for table Stationspath */
  lazy val Stationspath = new TableQuery(tag => new Stationspath(tag))

  /** Entity class storing rows of table Users
   *  @param userid Database column UserId SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param name Database column Name SqlType(VARCHAR), Length(50,true)
   *  @param gender Database column Gender SqlType(TINYINT UNSIGNED)
   *  @param birthday Database column BirthDay SqlType(DATETIME)
   *  @param job Database column Job SqlType(TINYINT UNSIGNED)
   *  @param areacode Database column AreaCode SqlType(TINYINT UNSIGNED)
   *  @param status Database column Status SqlType(TINYINT), Default(0)
   *  @param registerdate Database column RegisterDate SqlType(DATETIME)
   *  @param updatedate Database column UpdateDate SqlType(DATETIME) */
  final case class UsersRow(userid: Int, name: String, gender: Byte, birthday: java.sql.Timestamp, job: Byte, areacode: Byte, status: Byte = 0, registerdate: java.sql.Timestamp, updatedate: java.sql.Timestamp)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Byte], e3: GR[java.sql.Timestamp]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[Byte], <<[java.sql.Timestamp], <<[Byte], <<[Byte], <<[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, Some("Test"), "Users") {
    def * = (userid, name, gender, birthday, job, areacode, status, registerdate, updatedate) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userid), Rep.Some(name), Rep.Some(gender), Rep.Some(birthday), Rep.Some(job), Rep.Some(areacode), Rep.Some(status), Rep.Some(registerdate), Rep.Some(updatedate)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column UserId SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val userid: Rep[Int] = column[Int]("UserId", O.AutoInc, O.PrimaryKey)
    /** Database column Name SqlType(VARCHAR), Length(50,true) */
    val name: Rep[String] = column[String]("Name", O.Length(50,varying=true))
    /** Database column Gender SqlType(TINYINT UNSIGNED) */
    val gender: Rep[Byte] = column[Byte]("Gender")
    /** Database column BirthDay SqlType(DATETIME) */
    val birthday: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("BirthDay")
    /** Database column Job SqlType(TINYINT UNSIGNED) */
    val job: Rep[Byte] = column[Byte]("Job")
    /** Database column AreaCode SqlType(TINYINT UNSIGNED) */
    val areacode: Rep[Byte] = column[Byte]("AreaCode")
    /** Database column Status SqlType(TINYINT), Default(0) */
    val status: Rep[Byte] = column[Byte]("Status", O.Default(0))
    /** Database column RegisterDate SqlType(DATETIME) */
    val registerdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("RegisterDate")
    /** Database column UpdateDate SqlType(DATETIME) */
    val updatedate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UpdateDate")
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
