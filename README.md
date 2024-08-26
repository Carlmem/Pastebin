# Pastebin

1. [Installation](#installation)
2. [Configuration](#Configuration)
3. [Usage](#usage)

## Installation

To install and run this project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/Carlmem/Pastebin.git
2. Navigate to the project directory:
   ```bash
   cd Pastebin
3. Build the project using Gradle:
   ```bash
   ./gradlew build

4. Build docker images.
   ```bash
   docker-compose build

## Configuration
1. You need only configure your Amazon S3 data, which is in communication-service application.properties file.
   ```bash
   aws.access.key=${AWS_ACCESS_KEY:12345}
   aws.secret.key=${AWS_SECRET_KEY:12345}
   aws.s3.bucket=${AWS_S3_BUCKET:carlmem-pastebin-bucket}

## Usage

1. Navigate to the hash project's directory.
2. Run the hash-service docker-compose file.
   ```bash
   docker-compose up
 
3. Run a hash subproject.
4. Navigate to the communication project's directory. 
5. Run the communication-service docker-compose file.
   ```bash
   docker-compose up
6. There are 3 endpoints in total we will start from the hash-service.
![generate endpoint will return a hash, which will use a communication service to create a URL for a new post](https://imgur.com/3bytRIq)
![Next is creating content that will return a hash for the new URL,
   which you can use in the next step. At first, you need to add a pre-script for the date.](https://imgur.com/7kppaQl)
![Then you can send request for creating URL](https://imgur.com/zI9Xi7s)
![There's only one endpoint left, which will return your data by hash](https://imgur.com/GvWc8B2)
