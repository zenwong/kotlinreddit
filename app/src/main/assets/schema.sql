create table if not exists posts (
	_id integer primary key autoincrement,
	rid text,
	title text,
	url text,
	author text,
	subreddit text,
	media_title text,
	media_preview text,
	preview text,
	thumbnail text,
	posthint text,
	permalink text,
	comments integer,
	score integer,
	created integer,
	clicked integer,
	unique(rid) on conflict ignore
);

create table if not exists comments (
	_id integer primary key autoincrement,
	pid integer,
	cid text,
	parent text,
	author text,
	body text,
	score integer,
	created integer,
	foreign key(pid) references posts(_id) on delete cascade,
	unique(cid) on conflict ignore
);

create table if not exists messages (
	_id integer primary key autoincrement,
	mid text,
	title text,
	dest text,
	author text,
	parent text,
	created integer,
	unique(mid) on conflict ignore
);
