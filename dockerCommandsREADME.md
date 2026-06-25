If we change the Backend code then we might have to do,

docker compose down

Rebuild and start again:

docker compose up --build

The --build option tells Docker:

"Don't use the old backend image. Build a new one from the latest source code."

Only restart the application (No code changes)

If you didn't change any code and just want to restart:

docker compose restart

or

docker compose down
docker compose up

If you changed the Angular application:

docker compose up --build

Docker rebuilds the Angular image with the latest UI.

What if I changed only the backend?

Currently, Docker Compose rebuilds all services when you use --build.

A faster approach is to rebuild only the backend:

docker compose up --build backend

or

docker compose build backend
docker compose up backend

This saves time because PostgreSQL and Angular don't need rebuilding.

What if Docker still uses the old code?

Sometimes Docker uses cached layers.

Force a rebuild:

docker compose build --no-cache backend

First do:
docker compose down

Then:

docker compose up backend

or

docker compose up --build


Quick Reference
Change	Command
Backend code	docker compose up --build backend
Angular code	docker compose up --build frontend
Both backend & frontend	docker compose up --build
Only restart containers	docker compose restart
Rebuild without cache	docker compose build --no-cache backend