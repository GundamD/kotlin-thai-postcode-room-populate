import { Database, Statement } from "sqlite3"
import { ThaiAddress } from "./data/ThaiAddress"

/**
 * Populates DB with data
 */
export async function populate(
  db: Database,
  address: Array<ThaiAddress>
): Promise<void> {
  console.log("Populating addresses database...")
  await populateAddress(db, address)
}

/**
 * Sample data validation
 * @param address
 */
function isValidData(address: ThaiAddress): Boolean {
  return (
    address.province.length > 0 &&
    address.amphoe.length > 0 &&
    address.district.length > 0
  )
}

/**
 * Populates addresses
 */
async function populateAddress(db: Database, addresses: Array<ThaiAddress>) {
  console.log("Populating addresses table...")
  let validRecords = 0
  let invalidRecords: Array<number> = []

  db.serialize()
  await new Promise<void>((resolve, reject) => {
    db.run("begin transaction", function (err: Error) {
      if (null != err) {
        reject(err)
      } else {
        resolve()
      }
    })
  })
  const stmt = await new Promise<Statement>((resolve, reject) => {
    db.prepare("REPLACE INTO thai_address VALUES (?,?,?,?,?,?,?)", function (
      this: Statement,
      err: Error
    ) {
      if (null != err) {
        reject(err)
      } else {
        resolve(this)
      }
    })
  })

  for (const address of addresses) {
    if (false == isValidData(address)) {
      console.warn(`Invalid address: ${address.district_code}`)
      invalidRecords.push(address.district_code)
      continue
    }
    await new Promise<void>((resolve, reject) => {
      stmt.run(
        [
          address.district,
          address.amphoe,
          address.province,
          address.zipcode,
          address.district_code,
          address.amphoe_code,
          address.province_code,
        ],
        function (err: Error) {
          if (null != err) {
            reject(err)
          } else {
            validRecords++
            resolve()
          }
        }
      )
    })
  }
  console.log("addresses populated.")
  console.log(`Valid records: ${validRecords}`)
  console.log(`Invalid records (${invalidRecords.length}):`, invalidRecords)
  await new Promise<void>((resolve, reject) => {
    stmt.finalize(function (err: Error) {
      if (null != err) {
        reject(err)
      } else {
        resolve()
      }
    })
  })
  await new Promise<void>((resolve, reject) => {
    db.run("commit", function (err: Error) {
      if (null != err) {
        reject(err)
      } else {
        resolve()
      }
    })
  })
}
