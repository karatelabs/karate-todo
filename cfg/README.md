# Build and Deployment Scripts

For now these instructions are only for mac / linux.

If needed, make the `*.sh` files executable by running `chmod +x <path to file>`

## Docker

To package only the application (front-end and back-end) into a Docker container and run it:

```bash
cfg/build-docker-app.sh
```

To run the application on port 8080:

```bash
docker run -it --rm -p 8080:8080 karate-todo
```

Now you can access the application at [http://localhost:8080](http://localhost:8080).